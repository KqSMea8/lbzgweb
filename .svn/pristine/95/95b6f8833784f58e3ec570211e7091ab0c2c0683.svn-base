<template>
    <div class="page purchase-order-edit">
        <div class="page-bar">
            <LayoutHor>
                <div slot="left">
                    <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
                </div>
            </LayoutHor>
        </div>
        <div>
            <div class="subheader">
                单据明细
            </div>
            <div class="inquire-detail-card editable">
                <div>1.原辅材料目视检验1001</div>
                <div  class="editable-table" >
                    <table >
                        <thead>
                        <th>供应商</th>
                        <th>物料名称</th>
                        <th width="60">数量</th>
                        <th width="60">批次号</th>
                        <th>检验日期</th>
                        </thead>
                        <tbody>
                        <tr>
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
                                <!--<Input type="date" placeholder="" v-model="nowDate" readonly="readonly"></Input>-->
                                <!--{{nowDate}}-->
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <hr>
                    <br/>
                    <table>
                        <thead>
                        <th width="380">检验项目；标准</th>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <Input  type="text" v-model="list.inspectionItem" />
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <table >
                        <thead>
                        <th >化验员</th>
                        <th >检验员</th>
                        <th >审核人</th>
                        <th >审核日期</th>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                {{this.analyst}}
                            </td>
                            <td>
                                {{this.inspector}}
                            </td>
                            <td>
                                {{this.auditor}}
                            </td>
                            <td>
                                {{list.detailList[0].auditTime}}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <hr>
                </div>
            </div>
        </div>
        <table class="savebar" cellpadding="0" cellspacing="0">
            <tr>
                <td class="save" @click="save" v-if="pageFlag<=2">
                    保存
                </td>
                <td class="reset" @click="reset">
                    重置（取消）
                </td>
                <td></td>
            </tr>
        </table>
        <SelectCustomer ref="selectCustomer"></SelectCustomer>
        <SelContacts ref="selContacts"></SelContacts><!-- 新加部门选择控件 -->
    </div>
</template>
<script>
    import Loading from '@/components/loading';
    import LayoutHor from '@/components/layout/LayoutHor';
    import SelectCustomer from '@/components/customer/SelectCustomer';
    import SelContacts from '@/components/selcontacts'; //引用选择部门控件
    import page from '@/assets/js/page';
    import floatObj from '@/assets/js/floatObj';
    import process from '@/components/process-start';



    export default {
        components: {
            SelContacts,
            Loading,
        },
        data() {
            return {
                loading: 0,
                purchaseOrderId:'',
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
                Object.assign(this.detailList,{
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
                    "createTime":page.formatDate(new Date(),'yyyy-MM-dd'),
                    "supplement":page.formatDate(new Date(),'yyyy-MM-dd'),
                });
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
                var sel = this.$refs.selContacts;
                sel.show({
                    isMulti:false,
                    selectPerson:true,
                    selectDept:false,
                    ok:()=>{
                        if(sel.select.length>0){
                            this.list[index].proposerName1=sel.select[0].title;
                            // Object.assign(this.list,  this.list);
                            this.list.push();

                            //this.item.proposerName = sel.select[0].title;
                            // this.formItem.proposer = sel.select[0].key;
                        }
                    }
                });
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
                /*var form={
                    list:[

                    ]
                   };*/
                /*var res = JSON.stringify(form);*/
                /*console.log(res);*/

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
            reset(){
                console.log(this.pageFlag);
                if(this.pageFlag == 1){
                    this.initNew();
                }else {
                    Object.assign(this.detailList,this.oriItem);
                }
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
