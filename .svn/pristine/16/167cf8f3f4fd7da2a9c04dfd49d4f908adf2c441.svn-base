<template>
  <ListPage title="采购订单" ref="page" api="/api/order/list" 
  :model="this" :beforeLoad="beforeLoad"> 
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
          <td><Button @click="add" v-power="'cggl.cgdd.add'" icon="plus">新建</Button></td>
        </tr>
      </table>
    </div> 
    <div class="page-searchbox">
      <table cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <Input v-model="queryForm.purchaseOrderId" placeholder="单号" ></Input>
          </td> 
          <td>
            <Select v-model="queryForm.subType" style="width:150px" placeholder="采购类别">
              <Option v-for="item in types" :value="item.argCode" :key="item.argCode">{{ item.argText }}</Option>
            </Select>
          </td>
          <td>采购日期</td>
          <td><DatePicker type="daterange" v-model="queryForm.createTime"
           split-panels placeholder="采购日期"
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
  import page from '@/assets/js/page';

  export default {
    components: {
      ListPage,
      DataRowOperateBar, 
    },
    data() {
      let that = this;
      return {
        columns: [
          {
            title: '单号',
            key: 'purchaseOrderId',
            width: 140,
            align: 'center',
            render:(h,params)=>{
              var row = params.row;
              var text = row.purchaseOrderId;
              text = text;
              return h('a',{
                props:{

                },
                on:{
                  click:()=>{
                    this.$router.push({path:'/purchase-order/view?forword&id='+row.purchaseOrderId});
                  }
                }
              },text);
            }
          }, 
          page.table.initDateColumn({
            title: '采购日期',
            key: 'createTime',
          }),
          page.table.initPersonColumn({
            title: '采购人',
            key: 'proposer',
          }),
          page.table.initDepartmentColumn({
            title: '采购部门',
            key: 'department',
          }),
          {
            title: '质量要求',
            key: 'quality',
            align: 'left',
          },
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
            title: '审核',
            key: 'status',
            data:{
              '0':'创建',
              '1':'审核中',
              '2':'通过',
              '3':'驳回',
            }
          }),
        ],
        list: [],
        total:0,
        queryParam: {},
        queryForm: {
          subType:'',
          status:null,
          purchaseOrderId:'',
          createTime:null
        },
        loading: 0,
        types:[
         {argCode:'1',argText:'设备配件'},
         {argCode:'2',argText:'常规物料'},
         {argCode:'3',argText:'办公用品'}
        ],
      }
    },
    mounted: function () {
      this.reset();
    },
    computed: {},
    methods: { 
      query() { 
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
      reset() {
        Object.assign(this.queryForm,{
          subType:'',
          status:null,
          purchaseOrderId:'',
          createTime:[page.formatDate(new Date(new Date().getTime()-1000*60*60*24*60)),page.formatDate(new Date())]
        });
        this.query();
      },   
      add: function () {
        this.$router.push({path:'/purchase-order/edit?forward'})
      },
      update: function (item) {
        this.$refs.edit.open(item);
      }
    }
  }

</script>

<style type="text/css">
  
</style>
