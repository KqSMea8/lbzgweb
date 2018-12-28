<template>
    <Modal v-model="display" title="采购报检单" :closable="true" :mask-closable="false" width="1000" class="selcustomer" :transfer="transfer">
        <div class="page">
            <div class="page-searchbox">
                <table cellpadding="0" cellspacing="10">
                    <tr>
                        <td>采购申请单号：</td>
                        <td>
                            <Input v-model="queryForm.purchaseOrderId" placeholder="采购申请单号" ></Input>
                        </td>
                        <td>报检单号：</td>
                        <td>
                            <Input v-model="queryForm.inspectionApplyId" placeholder="报检单号" ></Input>
                        </td>
                        <td>申购部门：</td>
                        <td>
                            <Input v-model="queryForm.department"  readonly="readonly" @on-click="selDept" placeholder="申购部门" class="width-1"  icon="search" />
                        </td>
                    </tr>
                    <tr style="margin:15px 0px;!important;">
                        <td>采购员：</td>
                        <td>
                            <Input v-model="queryForm.proposer " readonly="readonly"
                                   style="cursor: pointer;"  placeholder="采购员" @click.native="selEmp" icon="search"></Input>
                        </td>
                        <td>选择日期：</td>
                        <td> <!--v-model="queryForm.createTime"-->
                            <DatePicker type="daterange" placement="bottom-end" v-model="createTime" placeholder="选择时间" style="width: 200px"></DatePicker>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <Button @click="onOK"  type="primary" icon="checkmark">确 认</Button>
                        </td>
                        <td>
                            <Button @click="onOK"  type="primary" icon="checkmark">反 确 认</Button>
                        </td>
                        <td>
                            <Button @click="onCancel"  type="dashed" >取 消</Button>
                        </td>

                        <td>
                            <Button @click="query"  type="info" icon="ios-search">查询</Button>
                        </td>
                        <td>
                            <Button @click="reset"   type="dashed">重置</Button>
                        </td>
                    </tr>
                </table>
            </div>
            <Loading :loading="loading">
                <div class="page-datatable">
                    <i-table :row-class-name="rowClassName"
                             :columns="columns"
                             @on-row-click="innerCheckRow(arguments[1])"
                             :data="list"></i-table>



                    <div style="height:10px;"></div>

                    <Page ref="pagebar" :total="total" size="small"
                          @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>
                </div>
            </Loading>
        </div>
        <div slot="footer" class="footer">
        </div>
        <SelContacts ref="selContacts"></SelContacts> <!-- 新加部门选择控件 -->
    </Modal>

</template>
<script>
    import Loading from '@/components/loading';
    import SelContacts from '@/components/selcontacts'; //引用选择部门控件
    import page from '@/assets/js/page';
    export default {
        components: {
            Loading,
            SelContacts,
        },
        props:{
            transfer:{
                type:Boolean,
                default:true
            }
        },
        data() {
            var that = this;
            return {
                columns: [
                    {
                        title: '选择',
                        key: '_checked',
                        width: 60,
                        render:function(h,params) {
                            var row = params.row;
                            var index = params.index;
                            var props={
                                value:row._checked,
                            };
                            if(row.status == "2"){
                                props.disabled =true;
                            }
                            return h('Checkbox',{
                                props:props,
                                on:{
                                    'on-change':()=>{
                                        that.innerCheckRow(index);
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: '报检单号',
                        key: 'inspectionApplyId',
                    },
                    {
                        title: '采购申请单号',
                        key: 'purchaseOrderId',
                        align: 'left'
                    },
                    {
                        title: '过磅单号',
                        key: 'weightId',
                        align: 'left',
                    },
                    {
                        title: '报检人员',
                        key: 'creators',
                        width: 80,
                        align: 'center',
                    },
                    {
                        title: '报检日期',
                        key: 'createTime',
                        width: 80,
                        align: 'center',
                    },
                ],
                display:false,
                list: [],
                total:0,
                createTime:[],
                queryParam: {},
                queryForm: {
                    departmentName:'',
                    department:'',
                    proposer:'',
                    proposerName:'',
                    creators:'',
                    createBeginTime:'',
                    createEndTime:'',
                    purchaseOrderId:'',
                    inspectionApplyId:'',
                },
                selected:[],
                loading: 0,
                options:{
                    ok:(data)=>{},
                    cancel:()=>{}
                }
            }
        },
        mounted: function () {
            this.query()
        },
        computed: {},
        methods: {
            load() {
                var pagebar = this.$refs.pagebar;
                this.loading = 1;
                this.queryParam.page = pagebar.currentPage;
                this.queryParam.pageSize = pagebar.currentPageSize;
                this.$http.post("/api/InspectionSheet/list",this.queryParam).then((res) => {
                    this.loading = 0;
                    var list = res.data.data.rows;
                    list.map((item)=>{item._checked=false;});
                    this.list = list;
                    this.total = res.data.data.total;
                    this.selection = [];
                    // 同步条件信息到表单
                    Object.assign(this.queryForm, this.queryParam);
                }).catch((error) => {
                    this.loading = 0;
                    this.$Message.error("请求失败，请重新发送")
                });
            },
            query: function () {
              this.queryForm.createBeginTime=page.formatDate(this.createTime[0]);
              this.queryForm.createEndTime=page.formatDate(this.createTime[1]);

                Object.assign(this.queryParam,this.queryForm)
                this.load();
            },
            pageChange: function (page) {
                this.load();
            },
            pageSizeChange:function(pageSize){
                var pagebar = this.$refs.pagebar;
                if(pagebar.currentPage == 1){
                    this.load();
                }
            },
            innerCheckRow(index){
                for(var i = 0;i<this.list.length;i++){
                    var item = this.list[i];
                    item._checked = index == i && item.status!=2;
                }
                console.log(this.list)
            },
            reset: function () {
                Object.assign(this.queryForm,{
                  departmentName:'',
                  department:'',
                  proposer:'',
                  proposerName:'',
                  creators:'',
                  createBeginTime:'',
                  createEndTime:'',
                  purchaseOrderId:'',
                  inspectionApplyId:'',
                });
               this.createTime=[];
                var pagebar = this.$refs.pagebar;
                pagebar.current = 1;
                pagebar.currentPage = 1;
                pagebar.currentPageSize = 10;

                this.load();
            },
            rowClassName(row,index){
                return row._checked?'row-checked':'';
            },
            // 对外方法
            show(options){
                Object.assign(this.options,{
                    ok:(data)=>{},
                    cancel:()=>{}
                },options);
                this.display = true;
                this.reset();
            },
            /*// 需要遗弃，请调用 show
            open() {
              this.display = true;
            },*/
            close() {
                this.display = false;
            },
            onOK() {
                var select = null;
                this.list.map((item)=>{
                    if(item._checked){
                        select = item;
                    }
                });
                if(select == null){
                  this.$Message.error('请选择报检单');
                  return;
                }
                this.display = false;
                this.options.ok(select);

                // 以下代码需要遗弃
                this.selected = [];
                this.list.map((item)=>{
                    if(item._checked){
                        this.selected.push(item);
                    }
                });
                this.display = false;

                this.$emit('on-ok',this.selected);
            },
            onCancel(){
                this.display = false;
            },
            //选择部门
            selDept(){
                var sel = this.$refs.selContacts;
                sel.show({
                    isMulti:false,
                    selectPerson:false,
                    selectDept:true,
                    ok:()=>{
                        if(sel.select.length>0){
                            this.queryForm.department = sel.select[0].title;
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
                            //alert(sel.select[0].title);
                            this.queryForm.proposer = sel.select[0].key;
                            this.queryForm.proposerName = sel.select[0].title;
                            // this.formItem.proposer = sel.select[0].key;
                        }
                    }
                });
            },
        }
    }

</script>

<style type="text/css">
    .selcustomer .page{
        padding: 0px;
    }
    .selcustomer .page-searchbox{
        margin-top: 0px;
    }
    .selcustomer .status-2{
        color:#ff6600;
    }
    .selcustomer .footer{
        text-align: center;
        padding-left: 10px;
    }
    .selcustomer .row-checked td{
        background-color: #e8f8fd;
        /*color:#20bfee;*/
        font-weight: bold;
    }
</style>
