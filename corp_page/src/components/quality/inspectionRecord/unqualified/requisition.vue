<template>
    <div class="page instock-edit">
        <div class="page-bar">
            <LayoutHor>
                <div slot="left">
                    <Button size="small" @click="goBack" icon="chevron-left"  type="warning">返回</Button>
                </div>
                <div class="page-bar-title">{{pageTitle}}</div>
            </LayoutHor>
        </div>
        <Loading :loading="loading">
            <div class="baseinfo">
                <div class="page-tools">
                </div>
                <!--       <div class="subheader">
                         调价单
                       </div>-->
                <Form ref="form" class="page-form" :model="formItem" :rules="formRules" :label-width="120">
                    <table cellspacing="0" cellpadding="0">
                        <colgroup>
                            <col width="33%">
                            <col width="auto">
                            <col width="33%">
                        </colgroup>
                        <tr>
                            <td>
                                <FormItem prop="sales" label="采购不合格品编号：">
                                    <Input :readonly="readonly" v-model="formItem.unqualifiedId" type="text" style="width:120px"></Input>
                                </FormItem></td>
                            <td> <FormItem prop="sales" label="采购申请单号：">
                                <Input :readonly="readonly" v-model="purchaseOrderId" type="text" style="width:120px"></Input>
                            </FormItem></td>
                            <td> <FormItem prop="sales" label="检验单号：">
                                <Input v-model="inspectionId" type="text" :readonly="readonly" style="width:120px"></Input>
                            </FormItem></td>
                            <td> <FormItem prop="sales" label="入库状态：">
                                <Select v-model="formItem.lnventoryStatus" :disabled="readonly"  style="width:200px">
                                    <Option value="" ></Option>
                                    <Option :value="1">未入库</Option>
                                    <Option :value="2">已入库</Option>
                                </Select>
                            </FormItem></td>
                        </tr>
                        <tr>
                            <td>
                                <FormItem prop="createTime" label="检验日期：">
                                    <DatePicker type="date" placeholder="" :readonly="readonly" v-model="formItem.createTime" format="yyyy-MM-dd" style="width:120px"></DatePicker>
                                </FormItem> </td>
                            <td> <FormItem prop="sales" label="检验员：">
                                <Input :readonly="readonly" v-model="inspector" type="text" style="width:120px"></Input>
                            </FormItem></td>
                            <td> <FormItem prop="sales" label="发票开具状态：">
                                <Select v-model="formItem.lnvoicingStatus" :disabled="readonly" style="width:200px">
                                    <Option value=""></Option>
                                    <Option :value="1">已开</Option>
                                    <Option :value="2">未开</Option>
                                </Select>
                            </FormItem></td>
                        </tr>
                    </table>
                </Form>
            </div>
            <div>
                <div class="subheader">
                    单据明细
                </div>

                <table border cellspacing="0" cellpadding="10">
                        <thead>
                        <th>序号</th>
                        <th>物料代码</th>
                        <th >物料名称</th>
                        <th >规格型号</th>
                        <th >品种</th>
                        <th >级别</th>
                        <th >供应商</th>
                        <th >产地</th>
                        <th >采购员</th>
                        <th >到货日期</th>
                        <th >单价</th>
                        <th >金额</th>
                        <th >原厂批号</th>
                        <th >厂批号</th>
                        <th >到货数量</th>
                        <th >车号</th>
                        <th >毛重</th>
                        <th >皮重</th>
                        <th >净重</th>
                        <th >生产日期</th>
                        <th >到厂日期</th>
                        <th >有效期</th>
                        <th >质保期</th>
                        <th >检验数量</th>
                        <th >不合格数量</th>
                        <th >单位</th>
                        <th >备注</th>
                        </thead>
                        <tbody>
                        <tr v-for="(item,index) in list"  :key="'mater_'+index" @click="curIndex = index">
                            <td :class="{'col-xh':true,'cur':index==curIndex}">
                                {{index+1}}
                            </td>
                            <td ><!-- @click="editable && !isImport &&  selMater(item)"-->
                                {{item.materId}}
                            </td>
                            <td >
                                {{item.materName}}
                            </td>
                            <td >
                                {{item.spec}}
                            </td>
                            <td >
                                {{item.subType}}
                            </td>
                            <td >
                                {{item.level}}
                            </td>
                            <td>
                                {{item.retestingPeriod}}
                            </td>
                            <td>
                                {{item.origin}}
                            </td>
                            <td>

                                {{item.buyer}}
                            </td>
                            <td>

                                {{item.arrivalDate}}
                            </td>
                            <td>

                                {{item.price}}
                            </td>
                            <td>

                                {{item.amount}}
                            </td>
                            <td>

                                {{item.originBatch}}
                            </td>
                            <td>

                                {{item.remark}}
                            </td>
                            <td>

                                {{item.arrivalQuantity}}
                            </td>
                            <td>

                                {{item.vehicleNo}}
                            </td>
                            <td>

                                {{item.roughWeight}}
                            </td>
                            <td>

                                {{item.packageWeight}}
                            </td>
                            <td>

                                {{item.remark}}
                            </td>
                            <td>

                                {{item.remark}}
                            </td>
                            <td>

                                {{item.remark}}
                            </td>
                            <td>

                                {{item.validityDate}}
                            </td>
                            <td>

                                {{item.qualityTime}}
                            </td>
                            <td>

                                {{item.unit}}
                            </td>
                            <td>
                                {{item.remark}}
                            </td>
                            <td>
                                {{item.remark}}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <SelectMaterial ref="selmaterial" :transfer="true"></SelectMaterial>
                    <SelProvider ref="selProvider"></SelProvider>
                    <SelectPlace ref="selPlace"></SelectPlace>
            </div>
            <table class="savebar" cellpadding="0" cellspacing="10px">
                <tr style="padding-top: 20px">
                    <td>
                        不合格品原因：
                        <Input v-model="formItem.wfInstRemark" :readonly="readonly" type="textarea" :rows="4" placeholder="不合格品原因..." />
                    </td>
                </tr>
                <tr>
                    <td style="padding-top: 20px">
                        <i-button @click="save" type="info" v-show="readonly==false">保存并提交</i-button>
                        <i-button @click="reset" type="info" v-show="readonly==false">取消</i-button>
                        <i-button @click="goBack">退出</i-button></td>
                </tr>
            </table>
        </Loading>
        <SelProvider ref="selProvider"></SelProvider>
    </div>
</template>
<script>
    import Loading from '@/components/loading';
    import LayoutHor from '@/components/layout/LayoutHor';
    import page from '@/assets/js/page';
    import floatObj from '@/assets/js/floatObj';
    import SelStorage from '@/components/storage/input/SelStorage';
    import SelStockOperType from '@/components/storage/input/SelStockOperType';
    import SelDeptInput from '@/components/selcontacts/SelDeptInput';
    import SelProvider from '@/components/provider/SelectProvider'; //导入选供应商的控件
    import SelPersonInput from '@/components/selcontacts/SelPersonInput';
    import Editable from '@/components/editable-table';
    import SelectMaterial from '@/components/purchase/provider-material/SelProviderMaterial'
    import SelectPlace from '@/components/storage/selplace/SelectPlace'

    export default {
        components: {
            Loading,
            LayoutHor,
            Editable,
            SelStorage,
            SelProvider,
            SelStockOperType,
            floatObj,
            SelDeptInput,
            SelPersonInput,
        },
        data() {
            return {
              pageTitle:'',
                loading: 0,
                param:{
                    wuliaobianma:'',
                    caigoubianhao:'',
                    jianyandanhao:'',
                },
                formItem:{
                    providerName:'',
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
                    lnventoryStatus:'',
                    lnvoicingStatus:'',
                    createTime:page.formatDate(new Date(),'yyyy-MM-dd'),
                    sales:this.$user.empInfo.trueName,
                },
                formRules:{
                    providerName: [
                        { required: true, message: '请选择供货商' }
                    ],
                },
                readonly:false,
                detailList:[],
                list:[],
                oriItem:{},
                storage:[],
                browse:0,
            }
        },
        mounted: function () {
            this.purchaseOrderId = this.$route.query.purchaseOrderId;
            this.inspectionId = this.$route.query.inspectionId;
            this.inspector=this.formItem.sales,
            this.instrumentId = this.$route.query.id;
            this.quantity = this.$route.query.quantity;
            this.unqualifiedId = this.$route.query.unqualifiedId;
            this.purchaseOrderId = this.$route.query.purchaseOrderId;
            this.checkQuantity=  this.$route.query.arrivalQuantity;
            this.browse=  this.$route.query.browse;
            this.load();
        },
        computed: {
            pageTitle(){
                return '编辑采购不合格品通知单';
            }
        },
        methods: {
            load() {
                this.loading = 1;
                if(this.browse==1){
                  this.readonly=true;
                  this.pageTitle = "采购不合格通知单详情";
                }
                this.$http.post("/api/UnqualifiedProductBill/list?purchaseOrderId="+this.purchaseOrderId+"&unqualifiedId="+this.unqualifiedId,{}).then((res) => {
                    this.loading = 0;
                    if (res.data.code == 0) {
                        if(res.data.data){
                          //Object.assign(this.list,res.data.data.rows[0]);
                          this.list = res.data.data.rows;
                          this.formItem = res.data.data.rows[0];
                          this.inspectionId=this.formItem.inspectionId;
                        }else{
                            this.$Message.error('订单不存在！');
                            this.goBack();
                        }
                    } else {
                        this.$Message.error(res.data.message);
                        this.list = [];
                    }
                }).catch((error) => {
                    this.loading = 0;
                    this.$Message.error("操作失败！")
                });
            },

            save(){

                var form={

                };
                Object.assign(form,this.list[0]);
                // 提交
                //this.loading = 1;
                var uri = '/api/UnqualifiedProductBill/update?inspectionId='+this.inspectionId+"&unqualifiedId"+this.unqualifiedId;
                form.createTime=page.formatDate(form.createTime);
                this.$http.post(uri,form).then((res) => {
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
            onAmountChange(val){
                this.formItem.amount = val;
            },
            datePickerChange(val){
                this.formItem.signDate = val;
            },
            switStorage(){

            },
            reset(){
                Object.assign(this.formItem,this.oriItem);
            },
            selProvider(){
                var sel = this.$refs.selProvider;//引用该控件，赋值给变量对象
                sel.show({
                    ok:(data)=>{
                        if(data){
                            this.formItem.providerName = data.providerName;
                            this.formItem.providerId = data.providerId;
                            this.formItem.linkMan = data.linkMan;
                            this.formItem.linkPhone = data.linkPhone;
                            this.formItem.address = data.address;
                        }
                    }
                });
            },
          goBack(){
                this.$router.go('-1');
            },
            goImport(){
                var sel = this.$refs.selInStockApply;
                sel.show({
                    isMulti:false,
                    selectPerson:false,
                    selectDept:true,
                    ok:(select)=>{
                        if(select){
                            this.formItem.relatedBillId = select.relatedBillId;
                            this.formItem.relatedBillId1 = select.relatedBillId1;

                        }
                    }
                });
            },
        }
    }

</script>

<style type="text/css">
    .instock-edit.page{
        width: 900px;
        margin: 0 auto;
        padding: 10px 0px;
        position: relative;
    }
    .instock-edit .subheader{
        height: 34px;line-height: 34px;
        font-size:14px;
        border-bottom: 0px solid #efefef;
        margin-bottom: 10px;
        color:#20bfee;
    }
    .instock-edit .baseinfo{

    }
    .instock-edit .baseinfo .label{
        width: 80px;text-align: right;
        padding-right: 8px;
    }
    .instock-edit .baseinfo table{
        width: 100%;
    }
    .instock-edit .baseinfo table td{
        height: 40px;padding-right:4px;
    }

    .instock-edit .savebar{
        margin-top: 10px;
        height: 40px;
        width: 100%;
        border-collapse: collapse;
    }
    .instock-edit .savebar td{
        border: 1px solid #fefefe;
        font-size: 14px;
    }
    .instock-edit .savebar .save{
        width: 120px;
        border: 1px solid #20bfee;
        background-color: #20bfee;
        color: white;
        text-align: center;
        cursor: pointer;
    }
    .instock-edit .savebar .reset{
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

    .instock-edit .tooltip{
        padding:10px;
        background-color: #fafafa;
        border:1px solid #efefef;
        border-radius: 3px;
        color:#666;
        margin-top: 10px;
    }

    /* initNew(){
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
          this.formItem.signDate = page.formatDate(new Date(),'yyyy-MM-dd');
          if(this.$user.empInfo){
            this.formItem.sales = this.$user.empInfo.empId;
            this.formItem.sales = this.$user.empInfo.trueName;
          }
          this.list = [];
          this.list.push(this.$refs.editable.listNewRow());
          this.list.push(this.$refs.editable.listNewRow());
        },*/
</style>
