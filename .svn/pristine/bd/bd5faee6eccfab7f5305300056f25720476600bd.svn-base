<template>
    <ListPage ref="page" title="合格证管理" :api="api" :queryForm="queryForm" :list="list"
              :model="this" :beforeLoad="beforeLoad" @on_load_data="on_load_data" class="customerlist"
              style="width: 100%;height:auto;overflow: hidden;!important;">
        <div style="width: 100%;height:auto;overflow: hidden;!important;">
            <div class="page-searchbox">
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            图号：
                        </td>
                        <td>
                            <Input v-model="queryForm.drawing" placeholder="图号"></Input>
                        </td>
                        <td>

                            铸件号：
                        </td>
                        <td>
                            <Input v-model="queryForm.casting" placeholder="铸件号"></Input>
                        </td>
                        <td>
                            合同号：
                        </td>
                        <td>
                            <Input v-model="queryForm.contractId" placeholder="合同号"></Input>
                        </td>
                        <td>
                            客户名称：
                        </td>
                        <td>
                            <Input v-model="queryForm.customerName" placeholder="客户名称"></Input>
                        </td>
                        <td>
                            开始日期：
                        </td>
                        <td>
                            <DatePicker type="daterange" :start-date="new Date(2018,1,1)" :end-date="new Date()" placement="bottom-end"
                                        v-model="createTime" placeholder="开始日期" style="width: 200px"></DatePicker>
                        </td>


                        <td>
                            <Button @click="query" type="primary" icon="ios-search">查询</Button>
                        </td>
                        <td>
                            <Button @click="reset">重置</Button>
                        </td>
                    </tr>
                </table>

            </div>


            <div class="page-tools">
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <Button @click="addNew" icon="plus">新建合格证报告</Button>
                        </td>
                        <!-- <td><Button @click="add"  icon="plus">编辑</Button></td>
                         <td><Button @click="add"  icon="plus">浏览</Button></td>-->
                        <td>
                            <Button @click="returnBack" icon="plus">退出</Button>
                        </td>
                    </tr>
                </table>
            </div>
            <div>
                <table cellpadding="5" class="hgz" cellspacing="0" style="margin:10px auto;">
                    <thead>
                    <tr>
                        <th>外销</th>
                        <th>合同编号</th>
                        <th>行号</th>
                        <th>客户名称</th>
                        <th>物料名称</th>
                        <th>图号</th>
                        <th>铸件号</th>
                        <th>材质</th>
                        <th>单位</th>
                        <th>数量</th>
                        <th>交货日期</th>
                        <th class="hgz_tr">证明报告</th>
                        <th class="hgz_tr">驳回原因</th>
                        <th>驳回日期</th>
                    </tr>
                    </thead>
                    <tbody>
                    <template v-for="(item,index) in list" style="width: 100%;overflow: hidden;!important;">
                        <span style="display: none">{{ ss=0 }}</span>
                        <template v-for="(item2,index2) in item.maps">
                            <tr v-for="(item3,index3) in item2.castingCertificate.testItemObj">
                                <td :rowspan="item.rowIndex" v-if="ss==0"><span v-if="item.isForeign==1">&#10004;</span>
                                </td>
                                <td :rowspan="item.rowIndex" v-if="ss==0">{{ item.contactId }}</td>
                                <td>{{ ss+=1 }}</td>
                                <td>{{ item3.customerName }}</td>
                                <td>{{ item3.materName }}</td>
                                <td>{{ item3.drawing }}</td>
                                <td>{{ item3.casting }}</td>
                                <td>{{ item3.texture }}</td>
                                <td>{{ item3.unit }}</td>
                                <td>{{ item3.quantity }}</td>
                                <td>{{ item3.deliveryDate }}</td>
                                <!-- 证明报告 -->
                                <td class="hgz_tr" :rowspan="item2.castingCertificate.testItemObj.length"
                                    v-show="index3==0">
                                    <i-button type="primary" size="small" v-if="item2.castingCertificate.status==2"
                                              v-show="item2.castingCertificate.certificateId!=null"
                                              @click="casting_open(index,index2,index3)">铸件合格证
                                    </i-button>
                                    <i-button type="info" size="small" v-else
                                              v-show="item2.castingCertificate.certificateId!=null"
                                              @click="casting_open(index,index2,index3)">铸件合格证
                                    </i-button>
                                    <i-button type="primary" size="small"
                                              v-if="item2.magneticPowderInspection.status==2"
                                              v-show="item2.magneticPowderInspection.magneticId!=null"
                                              @click="ultrasonic_open(index,index2,index3)">超声波探伤检验报告
                                    </i-button>
                                    <i-button type="info" size="small" v-else
                                              v-show="item2.magneticPowderInspection.magneticId!=null"
                                              @click="ultrasonic_open(index,index2,index3)">超声波探伤检验报告
                                    </i-button>
                                    <i-button type="primary" size="small" v-if="item2.partSize.status==2"
                                              v-show="item2.ultrasonicTesting.ultrasonicId!=null"
                                              @click="magnetic_open(index,index2,index3)">磁粉探伤检验报告
                                    </i-button>
                                    <i-button type="info" size="small" v-else
                                              v-show="item2.ultrasonicTesting.ultrasonicId!=null"
                                              @click="magnetic_open(index,index2,index3)">磁粉探伤检验报告
                                    </i-button>
                                    <i-button type="primary" size="small" v-if="item2.ultrasonicTesting.status==2"
                                              v-show="item2.partSize.partSizeId!=null"
                                              @click="size_open(index,index2,index3)">零件加工尺寸报告
                                    </i-button>
                                    <i-button type="info" size="small" v-else v-show="item2.partSize.partSizeId!=null"
                                              @click="size_open(index,index2,index3)">零件加工尺寸报告
                                    </i-button>

                                </td>
                                <!-- 驳回原因 wfInstRemark-->
                                <td class="hgz_tr" :rowspan="item2.castingCertificate.testItemObj.length"
                                    v-show="index3==0">
                                    <p v-if="item2.castingCertificate.status==2" style="color:red;">
                                        铸件：{{item2.castingCertificate.wfInstRemark}}..</p>
                                    <p v-if="item2.magneticPowderInspection.status==2" style="color:red;">
                                        超声波：{{item2.magneticPowderInspection.wfInstRemark}}..</p>
                                    <p v-if="item2.partSize.status==2" style="color:red;">
                                        磁粉：{{item2.castingCertificate.wfInstRemark}}..</p>
                                    <p v-if="item2.ultrasonicTesting.status==2" style="color:red;">
                                        零件加工：{{item2.ultrasonicTesting.wfInstRemark}}..</p>
                                </td>
                                <!-- 驳回日期 -->
                                <td :rowspan="item2.castingCertificate.testItemObj.length" v-show="index3==0">
                                    <p v-if="item2.castingCertificate.status==2" style="color:red;">
                                        铸件：{{item2.castingCertificate.wfInstTime}}</p>
                                    <p v-if="item2.magneticPowderInspection.status==2" style="color:red;">
                                        超声波：{{item2.magneticPowderInspection.wfInstTime}}</p>
                                    <p v-if="item2.partSize.status==2" style="color:red;">
                                        磁粉：{{item2.castingCertificate.wfInstTime}}</p>
                                    <p v-if="item2.ultrasonicTesting.status==2" style="color:red;">
                                        零件加工：{{item2.ultrasonicTesting.wfInstRemark}}</p>
                                </td>
                            </tr>
                        </template>
                    </template>
                    </tbody>
                </table>
                <Casting ref="casting"></Casting>
                <Ultrasonic ref="ultrasonic"></Ultrasonic>
                <Injury ref="injury"></Injury>
                <Size ref="size"></Size>
            </div>
        </div>
    </ListPage>
</template>
<script>
  import ListPage from './ListPage'
  import page from '@/assets/js/page'
  import Casting from './qualification/casting'
  import Ultrasonic from './qualification/ultrasonic'
  import Injury from './qualification/injury'
  import Size from './qualification/size'

  export default {
    components: {
      page,
      ListPage,
      Casting,
      Ultrasonic,
      Injury,
      Size,
      // DataRowOperateBar,
    },
    data() {
      return {
        api: '/api/Certificate/listAllC',
        list: [],
        total: 0,
        queryParam: {},
        queryForm: {
          pageSize: 10,
          page: 1,
          drawing: '',
          casting: '',
          contractId: '',
          customerName: '',
          createBeginTime: '',
          createEndTime: '',
        },
        createTime: [],
        industry: [],
        loading: 0
      }
    },
    mounted() {
      this.query()
      //this.reset();
    },
    computed: {},
    methods: {
      query() {
        this.queryForm.createBeginTime = page.formatDate(this.createTime[0]);
        this.queryForm.createEndTime = page.formatDate(this.createTime[1]);
        this.$refs.page.query()
        this.load()
      },
      reset() {

        Object.assign(this.queryForm, {
          pageSize: 10,
          page: 1,
          drawing: '',
          casting: '',
          contractId: '',
          customerName: '',
          createBeginTime: '',
          createEndTime: '',
        })
        this.createTime=[];
        this.load()
      },
      load() {
        this.$http.post(this.api, this.queryForm).then((res) => {
          if (res.data.code === 0) {
            this.list = res.data.data.rows
            this.total = res.data.data.total

          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error)
        })
      },
      beforeLoad() {

      },
      addNew() {
        this.goPage('/certificate/construction/new')
      },
      on_load_data(rows) {
        this.list = rows
      },
      returnBack() {
        this.goPage('/certificate/index')
      },
      casting_open(index, index2, index3) {
        var certificateId = this.list[index].maps[index2].castingCertificate.certificateId
        this.$http.post('/api/Certificate/getC?certificateId=' + certificateId, {'certificateId': certificateId}).then((res) => {
          if (res.data.code === 0) {

            if (!$.isEmptyObject(res.data.data)) {
              this.$refs.casting.listOpen(res.data.data)
            } else {
              this.$Message.error('未获取到数据')
            }
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error)
        })
      },
      ultrasonic_open(index, index2, index3) {
        var ultrasonicId = this.list[index].maps[index2].ultrasonicTesting.ultrasonicId
        this.$http.post('/api/Certificate/getU?ultrasonicId=' + ultrasonicId, {'ultrasonicId': ultrasonicId}).then((res) => {
          if (res.data.code == 0) {
            if (!$.isEmptyObject(res.data.data)) {
              this.$refs.ultrasonic.listOpen(res.data.data)
            } else {
              this.$Message.error('未获取到数据')
            }

          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error)
        })
      },
      magnetic_open(index, index2, index3) {
        var magneticId = this.list[index].maps[index2].magneticPowderInspection.magneticId
        this.$http.post('/api/Certificate/getM?magneticId=' + magneticId, {'magneticId': magneticId}).then((res) => {
          if (res.data.code == 0) {
            if (!$.isEmptyObject(res.data.data)) {
              this.$refs.injury.listOpen(res.data.data)
            } else {
              this.$Message.error('未获取到数据')
            }

          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error)
        })
      },
      size_open(index, index2, index3) {
        var partSizeId = this.list[index].maps[index2].partSize.partSizeId
        this.$http.post('/api/Certificate/getP?partSizeId=' + partSizeId, {'partSizeId': partSizeId}).then((res) => {
          if (res.data.code == 0) {
            if (!$.isEmptyObject(res.data.data)) {
              this.$refs.size.listOpen(res.data.data)
            } else {
              this.$Message.error('未获取到数据')
            }

          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error)
        })
      },
      goPage(url) {
        this.$router.push(url)
      },
      pageChange() {

      }

    }
  }

</script>

<style type="text/css">
    .hgz td, .hgz th {
        padding: 10px 0px;
        width: 100px;
        text-align: center;
        border: 1px solid lightslategray;
    }

    .hgz .hgz_tr {
        width: 240px;
    }

    .hgz .hgz_tr td {
        width: 240px;

        display: block;
    }

    .hgz_tr button {
        margin: 2px auto;
    }

    .hgz .hgz_table_trOne td, .hgz .hgz_table_trOne th {
        padding: 0px auto;

    }


</style>
