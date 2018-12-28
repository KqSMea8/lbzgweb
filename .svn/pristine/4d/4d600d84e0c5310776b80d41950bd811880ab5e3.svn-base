<template>
<ListPage ref="page" title="合同" api="/api/contract/list" 
:model="this" 
:beforeLoad="beforeLoad">
  <div class="page-tools">
    <table cellpadding="0" cellspacing="0">
      <tr>
        <td>
          <RadioGroup v-model="queryForm.status" type="button" @on-change="query">
            <Radio :label="null">所有</Radio>
            <Radio :label="0">待提交</Radio>
            <Radio :label="1">审核中</Radio>
            <Radio :label="2">通过</Radio>
            <Radio :label="3">驳回</Radio>
          </RadioGroup>
        </td>
        <td><Button @click="add" v-power="'xtzs.ht.add'" icon="plus">新建</Button></td>
        <td><Button @click="shipping" icon="plus" v-if="queryForm.status==2">转发货单</Button></td>
      </tr>
    </table>
  </div>
  <div class="page-searchbox">
    <table cellpadding="0" cellspacing="0">
      <tr> 
        <td>
          <Input v-model="queryForm.keyword" placeholder="合同编号、客户名称" ></Input>
        </td>
        <td v-if="false">合同编号</td>
        <td >录入日期</td>
        <td><DatePicker type="daterange" v-model="queryForm.createTime"
         split-panels placeholder="录入日期"
         style="width: 180px" :clearable="false" ::transfer="true"></DatePicker></td>
        <td>
          <Button @click="query"  type="primary" icon="ios-search">查询</Button>
        </td>
        <td>
          <Button @click="reset" >重置</Button>
        </td>
      </tr>
    </table>
  </div> 
</ListPage>
</template>
<script>
  import ListPage from '@/components/page/ListPage';
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import Loading from '@/components/loading'; 
  import page from '@/assets/js/page';

  export default {
    components: {
      DataRowOperateBar,
      Loading, 
      ListPage
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '合同Id',
            key: 'contractId',
            width: 120,
            render:(h,params)=>{
              var row = params.row;
              var text = row.contractId;
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$router.push({path:'/contract/view?forward&id='+row.contractId});
                  }
                }
              },text);
            }
          }, 
          {
            title: '合同编号',
            key: 'contractNo',
            align: 'left',
            minWidth:140,
          },
          page.table.initDateColumn({
            title: '签署日期',
            key: 'signDate',
          }),
          page.table.customerNameColumn({
            
          }),
          {
            title: '合同金额',
            key: 'amount',
            align: 'right',
            width:100,
          },
          {
            title: '币种',
            key: 'currencyName',
            align: 'center',
            width:80,
          },
          page.table.initDateColumn({
            title: '录入日期',
            key: 'createTime',
          }),
          {
            title: '进度',
            key: 'a',
            align: 'center',
            width: 80,
            render:(h, params)=>{
              return h('span', '--');
            }
          },
          page.table.initMapColumn({
            title: '评审',
            key: 'status',
            width: 80,
            data:{
              '0':'创建',
              '1':'审核中',
              '2':'通过',
              '3':'驳回',
            }
          }),
          page.table.initMapColumn({
            title: '修订',
            key: 'revisionStatus',
            width: 80,
            data:{
              '0':'--',
              '1':'审核中',
              '2':'通过',
              '3':'驳回',
            }
          }),
          page.table.initMapColumn({
            title: '发货',
            key: 'shippingStatus',
            width: 80,
            data:{
              '0':'--',
              '1':'发货中',
              '2':'完成'
            }
          }),
        ],
         
        queryParam: {},
        queryForm: {
          keyword:'',
          status:null,
          createTime:null,
        },
        industry:[],
        loading: 0
      }
    },
    mounted: function () { 
      this.reset();
    },
    computed: {},
    methods: {
      query: function () {
        this.$refs.page.query();
      },
      beforeLoad(){
        var queryParam = this.$refs.page.queryParam;
        queryParam.createTimeStart = '';
        queryParam.createTimeEnd = '';
        delete queryParam.createTime;
        if(this.queryForm.createTime.length > 0){
          queryParam.createTimeStart = page.formatDate(this.queryForm.createTime[0]);
        }
        if(this.queryForm.createTime.length > 1){
          queryParam.createTimeEnd = page.formatDate(this.queryForm.createTime[1]);
        }
      }, 
      reset: function () {
        Object.assign(this.queryForm,{
          keyword:'',
          status:null,
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        }); 
        this.query();
      }, 
      rowCommand: function (name, params) {
        if(name === '启用' || name === '禁用'){
          var status = 1;
          if(params.row.status === 1 ) {
             status = 2
          }
          this.$http.post('/api/customer/update', {
            customerId: params.row.customerId,
            status: status
            }).then((res) => {
              if (res.data.code === 0) {
                this.query();
              } else {
                this.$Message.error(res.data.message)
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
            });
        }
        if (name === '编辑') {
          this.update(params.row);
          return;
        }
        if (name === '删除') {
          this.$Modal.confirm({
            title: '删除确认',
            content: '<p>删除后不能恢复，确定删除已选择的记录吗？</p>',
            onOk: () => {
              var aid = params.row.customerId;
              this.loading = 1;
              this.$http.post('/api/customer/delete?customerId=' + aid, {}).then((res) => {
                this.loading = 0;
                if (res.data.code === 0) {
                  this.$Message.success("删除成功");
                  this.load();
                } else {
                  this.$Message.error(res.data.message)
                }
              }).catch((error) => {
                this.loading = 0;
                this.$Message.error(error.toString())
              });
            }
          });
        }
        if (name === '查看') {
          this.$refs.view.open(params.row);
          this.$refs.view.$once('on-edit',()=>{
            this.update(params.row);
          });
        }
      },
      goBack: function () {
        this.$router.go(-1);
      },  
      add: function () {
        this.$router.push({path:'/contract/edit?forward'})
      },
      shipping:function(){
        var row = this.$refs.page.currentRow;
        var status = row.shippingStatus;
        if(row && status!=2){
          this.$router.push({path:'/shipping/edit?forward&contractId='+row.contractId})
        }else{
          this.$Message.error("该合同已发货完成，不能创建发货单");
        }
      },
      update: function (item) {
        this.$refs.edit.open(item);
      }
    },
    activated(){
      this.$refs.page.load();
    },
  }

</script>