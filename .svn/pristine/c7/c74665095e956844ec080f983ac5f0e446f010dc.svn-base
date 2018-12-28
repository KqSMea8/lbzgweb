<template>
    <div class="page purchase-order-edit">
        <div>
            <div class="page-tools">
                <table cellpadding="10" cellspacing="10">
                    <tr>
                        <td><Button @click="save"  icon="paper-airplane" v-if="pageFlag<=2">保存</Button></td>
                        <td><Button @click="reset" icon="edit">取消</Button></td>
                        <td><Button @click="goBack" icon="paper-airplane">退出</Button></td>
                    </tr>
                </table>
            </div>
            <table class="savebar" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        检验员：
                        <Input v-model="this.analyst" readonly="readonly"
                               style="cursor: pointer;width:100px;"  placeholder="检验员" @click.native="selEmp1(index)" icon="search"></Input>
                    </td>
                    <td>
                        <div class="dateClick">
                            检验日期：
                            <input type="text" placeholder="" disabled="disabled" v-model="list.detailList[0].auditTime" format="yyyy-MM-dd" style="width:120px"></input>
                        </div>
                    </td>
                    <td colspan="1">
                        上传附件:
                        <!--<UploadBox v-model="this.analyst" :readonly="!editable"></UploadBox>-->   <!-- formItem.files 调用上传附件的控件 -->
                    </td>
                    <td>
                        <div style="width:100%;">
                            <span class="two_float">试用结论：</span>
                               <select v-on:change="indexSelect" v-model="indexId" >
                                   <option v-for="option1 in YX" v-bind:value="option1.text" >{{option1.text}}</option>
                               </select>
                         </div>
                    </td>
                </tr>
                <br/>
            </table>
            <table cellpadding="0" cellspacing="0">
                <tr valign="top" type="list-style:none">
                    <td>说明:</td>
                    <td>
                        <textarea rows="5" style="width: 600px;resize: none;">
                        </textarea>
                    </td>
                </tr>
            </table>
            <br/>
            <div class="inquire-detail-card editable" >
                <div  class="editable-table" >
                    <table >
                        <thead>
                        <th class="col-xh">序号</th>
                        <th>新材料登记编号</th>
                        <th>供应商名称</th>
                        <th width="60">采购员</th>
                        <th width="60">物料代码</th>
                        <th>物料名称</th>
                        <th>规格型号</th>
                        <th>物料类别</th>
                        <th width="60">数量</th>
                        <th width="60">重量(kg)</th>
                        <th>单位</th>
                        </thead>
                        <tbody>
                        <tr>
                            <td>{{index+1}}</td>
                            <td>
                                {{list.detailList[0].providerName}}
                            </td>
                            <td>
                                {{list.detailList[0].materName}}
                            </td>
                            <td>
                                <Input type="number" v-model="list.detailList[0].quantity" />
                            </td>
                            <td class="col-cz">
                                {{batchId}}
                            </td>
                            <td>
                                {{ list.detailList[0].createTime }}
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                    <hr>
                </div>
            </div>
        </div>

       <SelectCustomer ref="selectCustomer"></SelectCustomer>
       <SelContacts ref="selContacts"></SelContacts><!-- 新加部门选择控件 -->
    </div>
</template>
<script>
    import Loading from '@/components/loading';
    import SelContacts from '@/components/selcontacts'; //引用选择部门控件
    import page from '@/assets/js/page';
    import floatObj from '@/assets/js/floatObj';
    import process from '@/components/process-start';
    import UploadBox from '@/components/upload/Index';


    export default {
        components: {
            SelContacts,
            Loading,
            UploadBox,
        },
        data() {
            return {
                loading: 0,
                purchaseOrderId:'',
                editable:true,
                pageFlag:1,//1.新建 2.编辑 3.修订
                detailList:{
                    "purchaseOrderId":"",
                    "department":"",
                    "departmentName":"",
                    "proposer":"",
                    "proposerName":"",
                    "remark":"",
                    "status":0,
                    "wfInstId":"",
                    "wfInstTime":"",
                    "wfInstRemark":null,
                },
                list:[],
                currencyArgs:[],
                oriItem:{},
                remark:'',
              YX:[{
                  text:'合格',
                },
                {
                  text:'不合格',
                  },]
            }
        },
        props:{
            /*list:{
                type:Array,
                default:function () {
                    var arr = {
                    }
                    return arr;
                }
            },*/
            editable:{
                type:Boolean,
                default:false
            },
            purchaseOrderId:{
                type:String,
                default:''
            },
        },
        mounted: function () {

            this.versionId = this.$route.query.versionId;
            this.batchId = this.$route.query.batchId;
            this.ledgerId=this.$route.query.ledgerId;


            /*this.analyst = this.$route.query.analyst;

            /*this.queryForm.proposer=this.$user.empInfo.empId;*/
            // this.list.detailList[0].createTime=page.formatDate(newDate(),'yyyy-MM-dd');

            if(this.versionId!=0){
                this.provider = this.$route.query.provider;
                this.materName = this.$route.query.materName;
                this.inspectionDate = this.$route.query.inspectionDate;
                this.analyst = this.$route.query.analyst;
                this.inspector = this.$route.query.inspector;
                this.auditor = this.$route.query.auditor;
                this.auditTime = this.$route.query.auditTime;
                this.load();
            }else{
                this.analyst=this.$user.empInfo.trueName;
                this.inspector=this.analyst;
                this.auditor=this.analyst;

                this.load();
            }
            /*this.load();*/

        },
        computed: {
            pageTitle(){
                if(this.pageFlag == 1){
                    return '采购订单 - 创建';
                }
                if(this.pageFlag == 2){
                    return '采购订单 - 编辑';
                }
                if(this.pageFlag == 3){
                    return '采购订单 - 修订';
                }
            }
        },
        methods: {
            load() {
                this.loading = 1;
                /*var nowDate = page.formatDate(new Date(),'yyyy-MM-dd');*/
                var url="/api/order/get?purchaseOrderId=C201810220019"+this.purchaseOrderId+"&batchId="+ this.batchId+"&versionId="+this.versionId;

                /*if (this.versionId!=0){
                    var url="/api/revision/getNewCheckVisualInspection?batchId=" + this.batchId+"&versionId="+this.versionId;
                  }*/

                this.$http.post(url,{}).then((res) => {
                    this.loading = 0;
                    if (res.data.code == 0) {
                        if(res.data.data){
                            this.list = res.data.data;
                            this.list.detailList[0].createTime=page.formatDate(new Date(),'yyyy-MM-dd');
                            this.list.detailList[0].auditTime=this.list.detailList[0].createTime;
                        }else{
                            this.$Message.error('订单不存在！');
                            //this.goBack();
                        }
                    } else {
                        this.$Message.error(res.data.message);
                    }
                }).catch((error) => {
                    this.loading = 0;
                    this.$Message.error("操作失败！")
                });

            },
            initNew(){
                Object.assign(this.formItem,{
                    pmapId:'',
                    materId:'',
                    materName:'',
                    spec:'',
                    model:'',
                    subType:'',
                    use:'',
                    price:'',
                    afterPrice:'',
                    remark:'',
                    createTime:page.formatDate(new Date(),'yyyy-MM-dd'),

                });
                this.detailList.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
                if(this.$user.empInfo){
                    this.detailList.proposer = this.$user.empInfo.empId;
                    this.detailList.proposerName = this.$user.empInfo.trueName;
                    this.formItem.sales = this.$user.empInfo.empId;
                    this.formItem.sales = this.$user.empInfo.trueName;
                }

                this.list = [];
                this.list.push(this.$refs.editable.listNewRow());
                this.list.push(this.$refs.editable.listNewRow());
            },
          selEmp1(index){
            if(this.isOnclick[index]){
              return;
            }
            var sel = this.$refs.selContacts;

            sel.show({
              isMulti:false,
              selectPerson:true,
              selectDept:false,
              ok:()=>{
                if(sel.select.length>0){
                  this.analyst=sel.select[0].title;
                  this.list.push();
                }
              }

            });

          },
          indexSelect(){
            this.A = this.indexId;
            // console.log(this.A);

          },
            selDept(){
                var sel = this.$refs.selContacts;
                sel.show({
                    isMulti:false,
                    selectPerson:false,
                    selectDept:true,
                    ok:()=>{
                        if(sel.select.length>0){
                            this.detailList.departmentName = sel.select[0].title;
                            this.detailList.department = sel.select[0].key;
                        }
                    }
                });
            },
            selEmp(){
                var sel = this.$refs.selContacts;
                sel.show({
                    isMulti:false,
                    selectPerson:true,
                    selectDept:false,
                    ok:()=>{
                        if(sel.select.length>0){
                            this.detailList.proposerName = sel.select[0].title;
                            this.detailList.proposer = sel.select[0].key;
                        }
                    }
                });
            },
            save(){
                var form =
                    {
                        operator:this.$user.empInfo.empId,
                        batchId:this.batchId,
                        versionId:this.versionId,
                        ledgerId:this.ledgerId,
                        quantity:this.list.detailList[0].quantity,
                        analyst:this.analyst,
                        inspector:this.inspector,
                        auditor:this.auditor,
                        auditTime:this.list.detailList[0].auditTime,
                        provider:this.list.detailList[0].providerName,
                        materName:this.list.detailList[0].materName,
                        inspectionDate:this.list.detailList[0].createTime,

                        inspectionItem:this.list.inspectionItem,
                        /*/!*!/!*detailList:this.list.detailList,*!/!*!/*/
                    };


                Object.assign(form,this.form);
                console.log(form);
                // 提交
                this.loading = 1;
                var uri = '/api/revision/addAllCheckVisualInspection';
                this.$http.post(uri, form).then((res) => {
                    this.loading = 0;

                    if (res.data.code == 0) {
                        this.$Message.success("操作成功！");
                        this.goBack();
                    } else {
                        this.$Message.error(res.data.message);
                    }
                }).catch((error) => {
                    this.loading = 0;
                    this.$Message.error("请求失败，请重新操作")
                });
            },

            onDrawingInput(item){
                if(this.detailList.customerId == null || this.detailList.customerId == ''){
                    this.$Message.error('请选择客户！');
                    return;
                }
                var materId = this.detailList.customerId + '-' + item.drawing;

                this.$http.post('/api/material/get?materId=' +materId, {}).then((res) => {
                    if (res.data.code === 0 && res.data.data !=null) {
                        item.materName = res.data.data.materName;
                        item.unit = res.data.data.unit;
                    }
                }).catch((error) => {
                    this.$Message.error(error.message)
                });
            },
            onAmountChange(val){
                this.detailList.amount = val;
            },
            datePickerChange(val){
                this.detailList.signDate = val;
            },
            /*reset(){
                console.log(this.pageFlag);
                if(this.pageFlag == 1){
                    this.initNew();
                }else {
                    Object.assign(this.detailList,this.oriItem);
                }
            },*/
          reset() {
            Object.assign(this.list,{
              inspectionItem:"",
            });
            this.query();
          },
            goBack(){
                /*this.$router.push('/quality/quality-record/RevisionRecord');*/
                this.$router.go(-1);
            }
        }
    }

</script>

<style type="text/css">
    .purchase-order-edit.page{
        width: 900px;
        margin: 0 auto;
        padding: 10px 0px;
        position: relative;
    }
    .purchase-order-edit .subheader{
        height: 34px;line-height: 34px;
        font-size:14px;
        border-bottom: 0px solid #efefef;
        margin-bottom: 10px;
        color:#20bfee;
    }
    .purchase-order-edit .baseinfo{

    }
    .purchase-order-edit .baseinfo .label{
        width: 80px;text-align: right;
        padding-right: 8px;
    }
    .purchase-order-edit .baseinfo table{
        width: 100%;
    }
    .purchase-order-edit .baseinfo table td{
        height: 40px;padding-right:4px;
    }

    .purchase-order-edit .savebar{
        margin-top: 10px;
        height: 40px;
        width: 100%;
        border-collapse: collapse;
    }
    .purchase-order-edit .savebar td{
        border: 1px solid #fefefe;
        font-size: 14px;
    }
    .purchase-order-edit .savebar .save{
        width: 120px;
        border: 1px solid #20bfee;
        background-color: #20bfee;
        color: white;
        text-align: center;
        cursor: pointer;
    }
    .purchase-order-edit .savebar .reset{
        width: 60px;
        border: 1px solid #a1e7f8;
        background-color: #a1e7f8;
        color: white;
        text-align: center;
        cursor: pointer;
    }

    .selectinput{
        cursor: pointer;
    }

    .purchase-order-edit .tooltip{
        padding:10px;
        background-color: #fafafa;
        border:1px solid #efefef;
        border-radius: 3px;
        color:#666;
        margin-top: 10px;
    }

    .inquire-detail-card.editable {

    }
    .inquire-detail-card .title{
        height: 40px;
        line-height: 38px;
        text-align: center;
        min-width: 89px;
        border: 1px solid #dedede;
        display: inline-block;
        border-bottom: 0px;font-weight: bold;
    }
    .inquire-detail-card .editable-table{
        overflow-x: auto;
    }
    .editable-table table{
        border-collapse:collapse;
        border:  px solid #dedede;
        width: 100%;
    }
    .editable-table table th{
        background: #efefef;
    }
    .inquire-detail-card .editable-table table td,
    .inquire-detail-card .editable-table table th{
        height: 40px;
        border: 1px solid #dedede;
        padding: 0 10px;text-align: center;
    }
    .editable-table .ivu-input,
    .editable-table .ivu-input-number,
    .editable-table .ivu-select,
    .editable-table .ivu-select-selection{
        border: 0px;
    }
    .editable-table .ivu-input-number-input{
        padding-right: 24px;
        text-align: right;
    }
    .editable-table .col-xh{
        text-align: center; cursor: default;
    }
    .editable-table .col-xh.cur{
        background: #e8f8fd;
        color:#20bfee;
        border-right: 2px solid #20bfee;
        cursor: default;font-weight: bold;;
    }
    /*行扩展操作*/
    .editable-table .col-cz input{
        text-align: center;;
    }
    .editable-table .col-yd input{
        text-align: center;;
    }
    .inquire-detail-card .editable-card{
        margin-top: 10px;
        margin-bottom: 10px;
    }

</style>
