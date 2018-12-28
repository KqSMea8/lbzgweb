<template>
    <ListPage ref="page" title="合格证管理" api="/api/contractProject/list"
              :model="this" :beforeLoad="beforeLoad" class="customerlist">

        <div class="page-searchbox">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        图号：
                    </td>
                    <td>
                        <Input v-model="queryForm.keyword" placeholder="图号"></Input>
                    </td>
                    <td>
                        铸件号：
                    </td>
                    <td>
                        <Input v-model="queryForm.keyword" placeholder="铸件号"></Input>
                    </td>
                    <td>
                        合同号：
                    </td>
                    <td>
                        <Input v-model="queryForm.keyword" placeholder="合同号"></Input>
                    </td>
                    <td>
                        客户名称：
                    </td>
                    <td>
                        <Input v-model="queryForm.keyword" placeholder="客户名称"></Input>
                    </td>
                    <td>
                        开始日期：
                    </td>
                    <td>
                        <DatePicker type="date" placeholder="" v-model="queryForm.reportTime2" format="yyyy-MM-dd"
                                    style="width:120px"></DatePicker>
                        &nbsp;&nbsp;至&nbsp;&nbsp;
                        <DatePicker type="date" placeholder="" v-model="queryForm.reportTime2" format="yyyy-MM-dd"
                                    style="width:120px"></DatePicker>
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
                    <td>
                        <Button @click="add" icon="plus">编辑</Button>
                    </td>
                    <td>
                        <Button @click="add" icon="plus">浏览</Button>
                    </td>
                    <td>
                        <Button @click="returnBack" icon="plus">退出</Button>
                    </td>
                </tr>
            </table>
        </div>

        <!--<ProviderEdit ref="edit" @on-save="query"></ProviderEdit>-->
    </ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage'
  import DataRowOperateBar from './DataRowOperateBar'
  import page from '@/assets/js/page'

  export default {
    components: {
      page,
      ListPage,
      DataRowOperateBar,
      /*ProviderEdit*/
    },
    data() {
      let that = this
      return {
        columns: [
          {
            title: '外销',
            key: 'isForeign',
            width: 100,
          },
          {
            title: '合同编号',
            key: 'customerId',
            align: 'left'
          },
          {
            title: '行号',
            key: 'count',
            align: 'left'
          },
          {
            title: '客户名称',
            key: 'customerName',
            width: 120,
            align: 'center',
          },
          {
            title: '物料名称',
            key: 'materName',
            width: 120,
            align: 'center',
          },
          {
            title: '图号',
            key: 'linkPhone',
            width: 120,
            align: 'center',
          },
          {
            title: '铸件号',
            key: 'linkPhone',
            width: 120,
            align: 'center',
          },
          {
            title: '材质',
            key: 'linkPhone',
            width: 120,
            align: 'center',
          },
          {
            title: '单位',
            key: 'linkPhone',
            width: 120,
            align: 'center',
          },
          {
            title: '数量',
            key: 'linkPhone',
            width: 120,
            align: 'center',
          },
          {
            title: '交货日期',
            key: 'linkPhone',
            width: 120,
            align: 'center',
          },

          {
            title: '证明报告',//操作按钮列表
            width: 280,
            align: 'center',
            render(h, params) {
              let status = params.row.status
              let setButton = '合格证,超声波探伤检验报告,磁粉探伤检验报告,零件加工尺寸报告'
              return h(DataRowOperateBar, {
                props: {
                  commands: setButton,
                  status: status,
                },
                on: {
                  'on-command': (name) => {
                    that.rowCommand(name, params)
                  }
                }
              })
            }
          },
          {
            title: '驳回原因',
            key: 'linkPhone',
            width: 120,
            align: 'center',
          },
          {
            title: '驳回日期',
            key: 'linkPhone',
            width: 120,
            align: 'center',
          },
        ],
        list: [],
        total: 0,
        queryParam: {},
        queryForm: {
          keyword: ''
        },
        industry: [],
        loading: 0
      }
    },
    mounted() {
      this.reset()
    },
    computed: {},
    methods: {
      query() {
        this.$refs.page.query()
      },
      reset() {

        Object.assign(this.queryForm, {
          keyword: ''
        })
        this.query()
      },
      beforeLoad() {

      },
      addNew() {
        this.goPage('/certificate/construction/new')
      },
      rowCommand(name, params) {
        if (name === '编辑') {
          this.update(params.row)
          return
        }

        if (name === '启用' || name === '禁用') {
          var status = 1
          if (params.row.status === 1) {
            status = 2
          }
          this.$http.post('/api/Certification/list', {
            providerId: params.row.providerId,
            status: status
          }).then((res) => {
            if (res.data.code === 0) {
              this.query()
            } else {
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
            this.$Message.error(error)
          })
        }
      },
      returnBack() {
        this.goPage('/certificate/index')
      },
      add() {
        this.$refs.edit.open({})
      },
      update(item) {
        this.$refs.edit.open(item)
      },
      goPage(url) {
        this.$router.push(url)
      }
    }
  }

</script>

<style type="text/css">
    .customerlist .status-2 {
        color: #ff6600;
    }


    /*          {
              title: '状态', //文字状态显示
              key: 'status',
              align: 'center',
              width:60,
              render:(h,params)=>{
                var status = params.row.status;
                var setButton = "正常";
                if (status == 2) {
                  setButton = "禁用";
                }
                return h('span',{class:'status-'+status},setButton);
              }
            },*/

    .ivu-btn.ivu-btn-text.sss span {
        color: red;
    }


</style>
