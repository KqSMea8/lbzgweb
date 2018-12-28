<template>
	<!-- 新建外检申请单 -->
	<div class="page purchase-order-edit">
		<div class="page-bar">
			<LayoutHor>
				<div slot="left">
					<Button size="small" @click="goBack" icon="chevron-left" type="warning">返回</Button>
				</div>
				<div class="page-bar-title">{{pageTitle}}</div>
			</LayoutHor>
		</div>
		<Loading :loading="loading">
			<div class="baseinfo">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td class="label">外检申请单号</td>
						<td><Input v-model="formItem.externalApplyId" disabled="disabled" placeholder="由系统自动生成"></Input></td>
						<td class="label">操作人</td>
						<td>
							<Input v-model="formItem.operatorName" readonly="readonly" style="cursor: pointer;" @click.native="selEmp"></Input>
						</td>
					</tr>
					<tr>
						<td class="label">申请日期</td>
						<td>
							<DatePicker type="date" placeholder="" v-model="formItem.applicationDate" format="yyyy-MM-dd" style="width:120px"></DatePicker>
						</td>
						<td class="label">申请部门</td>
						<td>
							<Input v-model="formItem.departmentName" style="cursor: pointer;" @click.native="selDept"></Input>
						</td>
					</tr>
					<tr>
						<td class="label">备注</td>
						<td class="col-remark">
							<Input v-model="formItem.remark" type="textarea" :rows="3"></Input>
						</td>
					</tr>
				</table>
			</div>
			<div>
				<!--<div class="subheader">
                    物料明细
                </div>-->

				<AddDetailEditable ref="editable" :list="list" :editable="true" :isNew="pageFlag==1" @on-amount-change="onAmountChange"></AddDetailEditable>
			</div>
			<table class="savebar" cellpadding="0" cellspacing="0">
				<tr>
					<td class="save" @click="save" v-if="pageFlag<=2">
						保存并提交
					</td>
					<td class="reset" @click="reset">
						重置
					</td>
					<td></td>
				</tr>
			</table>
		</Loading>
		<SelContacts ref="selContacts"></SelContacts>
	</div>
</template>
<script>
	import Loading from '@/components/loading';
  import LayoutHor from '@/components/layout/LayoutHor';
  import AddDetailEditable from './AddDetailEditable';
  import page from '@/assets/js/page';
  import SelContacts from '@/components/selcontacts';
	import process from '@/components/process-start';

  export default {
    components: {
      Loading,
      LayoutHor,
      AddDetailEditable,
      SelContacts
    },
    data() {
      return {
				fd:"",
        loading: 0,
        purchaseOrderId:'',
        pageFlag:1,//1.新建 2.带入
        formItem:{
            externalApplyId:"",
            operatorName:"",
            proposer:"",
            applicationDate:"",
            departmentName:"",
            department:'',
            remark:"",
        },
        list:[],
        currencyArgs:[],
        oriItem:{
         /* externalApplyId:"",
					proposer:"",
          operatorName:"",
          applicationDate:"",
					department:'',
          departmentName:"", 
          remark:"", */
					/* mater_id:"",
					counter_name:"",
					model:"",
					orgin:"",
					internal_id:"",
					installation_site:"",
					retesting_period:"",
					remarks:"", */
					detailList:[]
        },
				processRequireArgs:this.$args.argMap['process_require'],
        remark:'',
        instrumentId:"",
      }
    },
    mounted: function () {
        this.rukou();
    },
    computed: {
      pageTitle(){
        if(this.pageFlag == 1){
          return '计量器具管理- 新建外检申请单';
        }
        if(this.pageFlag == 2){
          return '计量器具管理 - 修改外检申请单';
        }
      }
    },
    watch: {
      // 如果路由有变化，会再次执行该方法
      '$route': 'rukou',

    },
    methods: {
      rukou(){
        Object.assign(this.formItem,{
          externalApplyId:"",
          operatorName:"",
          proposer:"",
          applicationDate:"",
          departmentName:"",
          department:'',
          remark:"",
        });
        this.purchaseOrderId = this.$route.query.id;
        this.instrumentId = this.$route.query.instrumentId;
        this.pageFlag = 1;
        if(this.purchaseOrderId){
          this.pageFlag = 2;
        }
        if(this.pageFlag == 1){
          this.initNew();
          this.load();
        }
        if(this.pageFlag == 2){
          this.initUpdate();
        }
      },
			
			
      load() {
        this.loading = 1;
        var itemDetail= this.$refs.editable;//引用到其他控件，赋给对象
        this.$http.post("/api/instrument/list",{instrumentIdStr:this.$route.query.instrumentId}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              this.list=[];
              //var def=res.data.data.rows;
              this.list=res.data.data.rows;
            }else{
              this.$Message.error('订单不存在！');
              this.goBack();
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

        this.formItem.applicationDate = page.formatDate(new Date(),'yyyy-MM-dd');
        if(this.$user.empInfo){
          this.formItem.proposer = this.$user.empInfo.empId;
          this.formItem.operatorName = this.$user.empInfo.trueName;
					this.formItem.department = this.$user.empInfo.deptId;
					this.formItem.departmentName = this.$args.getArgText('deptList',this.$user.empInfo.deptId);
        }
        //this.list = [];
        //this.list.push(this.$refs.editable.listNewRow());
        //this.list.push(this.$refs.editable.listNewRow());
      },
      initUpdate(){
        this.loading = 1;
        var itemDetail= this.$refs.editable;//引用到其他控件，赋给对象
        this.$http.post("/api/ExternalInspectionApply/findEIA",{externalApplyId:this.$route.query.id}).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            if(res.data.data){
              Object.assign(this.formItem,res.data.data.rows[0]);
            }else{
              this.$Message.error('订单不存在！');
              this.goBack();
            }
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("操作失败！")
        });
        this.$http.post("/api/ExternalInspectionApply/get?externalApplyId="+this.$route.query.id,{}).then((res) => {
          if (res.data.code == 0) {
            if(res.data.data){
              this.list=[];
                Object.assign(this.list,res.data.data);
              this.loading = 0;
            }else{
              this.$Message.error('订单不存在！');
              this.goBack();
            }
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("操作失败！")
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
              this.formItem.departmentName = sel.select[0].title;
              this.formItem.department = sel.select[0].key;
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
              //this.formItem.proposer = sel.select[0].key;
              //this.formItem.operator = sel.select[0].key;
            }
          }
        });
      },
			
			// 提交评审审核
			submitExternalApply(){
				this.$Modal.confirm({
					title: '确认提交审核',
					content: '<p>确定提交计量器具审核吗？</p>',
					
					onOk: () => {
						
						this.loading = 1; 
// 						process.startExternalApplyAudit(this.oriItem,(err,Instid)=>{
// 							// 可以发起流
// 							// this.oriItem  是空,应该传入单据对象(单据对象应该是先保存到数据库,然后就有单号)						
// 							//process.startAudit(this.oriItem,(err,instid)=>{ 
// 							this.loading = 0;
// 							if(err){
// 								this.$Message.error(err.msg);
// 							}else{
// 								this.submitAuditCallBack('',Instid);
// 							}
// 						});
						this.$http.post('/api/ExternalInspectionApply/submit',this.oriItem).then((res) => {
              this.loading = 0;
              if (res.data.code == 0) {
                this.$Message.success('提交成功！');
                this.goBack();
              } else {
                this.$Message.error(res.data.message);
                this.load();
              }
            }).catch((error) => {
              this.loading = 0;
              this.$Message.error("操作失败！")
            });	
					}
				});
			},
		 submitAuditCallBack(type,Instid){
		        var api =  '/api/ExternalInspectionApply/submit';
		        var data = {instrumentId:this.instrumentId,externalApplyId:this.oriItem.externalApplyId};
		        if(type == ''){
		          data.wfInstId = Instid;
		        }
		
		        this.loading = 1;
		        this.$http.post(api,data).then((res) => {
		          this.loading = 0;
		          if (res.data.code == 0) {
		            this.$Message.success('提交成功！');
		            //this.load();
								this.goBack();
		          } else {
		            this.$Message.error(res.data.message);
		           // this.load();
							 this.goBack();
		          }
		        }).catch((error) => {
		          this.loading = 0;
		          this.$Message.error("操作失败！")
		        });
		      },


     //保存并提交
     save(){

        var form = {
          detailList:[]
        };

        Object.assign(form,this.formItem);
				Object.assign(this.oriItem,form);
        form.applicationDate = page.formatDate(form.applicationDate);
        form.detailList = [];
        for(var i = 0;i<this.list.length;i++){
          var item = this.list[i];
          if(item.materId != ''){
            if(item.quantity == 0){
              this.$Message.error('物料 ' + item.materId + ' ,请录入数量');
              return;
            }
            item.needDate = page.formatDate(item.needDate);
            form.detailList.push(item);
          }
					Object.assign(this.oriItem.detailList,form.detailList);
        }

        // 提交
        this.loading = 1;
				if(this.pageFlag == 1){
					form.createTime = page.formatDate(new Date(),'yyyy-MM-dd');
					var uri = '/api/ExternalInspectionApply/insert';
				}else if(this.pageFlag == 2){
              uri = '/api/ExternalInspectionApply/update';
          }else{
						this.$Message.error("未知错误,请重新进入页面尝试");
					}
            console.log(form);
        this.$http.post(uri, form).then((res) => {
          this.loading = 0;
          if (res.data.code == 0) {
            //this.$Message.success("操作成功！");
						if(res.data.data==''){
							this.$Message.error("未获取到外检申请单号");
						}else{
							this.oriItem.externalApplyId=res.data.data;
						}
						this.submitExternalApply();
            //this.goBack();
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
      reset(){
        if(this.pageFlag == 1){
          Object.assign(this.formItem,this.oriItem);
          this.initNew();
        }else {
          Object.assign(this.formItem,this.oriItem);
          this.initUpdate();
        }
      },
      goBack(){
        this.$router.go(-1);
      }
    }
  }

</script>

<style type="text/css">
	.purchase-order-edit.page {
		width: 900px;
		margin: 0 auto;
		padding: 10px 0px;
		position: relative;
	}

	.purchase-order-edit .subheader {
		height: 34px;
		line-height: 34px;
		font-size: 14px;
		border-bottom: 0px solid #efefef;
		margin-bottom: 10px;
		color: #20bfee;
	}

	.purchase-order-edit .baseinfo {}

	.purchase-order-edit .baseinfo .label {
		width: 80px;
		text-align: right;
		padding-right: 8px;
	}

	.purchase-order-edit .baseinfo table {
		width: 100%;
	}

	.purchase-order-edit .baseinfo table td {
		height: 40px;
		padding-right: 4px;
	}

	.purchase-order-edit .savebar {
		margin-top: 10px;
		height: 40px;
		width: 100%;
		border-collapse: collapse;
	}

	.purchase-order-edit .savebar td {
		border: 1px solid #fefefe;
		font-size: 14px;
	}

	.purchase-order-edit .savebar .save {
		width: 120px;
		border: 1px solid #20bfee;
		background-color: #20bfee;
		color: white;
		text-align: center;
		cursor: pointer;
	}

	.purchase-order-edit .savebar .reset {
		width: 60px;
		border: 1px solid #a1e7f8;
		background-color: #a1e7f8;
		color: white;
		text-align: center;
		cursor: pointer;
	}

	.selectinput {
		cursor: pointer;
	}

	.purchase-order-edit .tooltip {
		padding: 10px;
		background-color: #fafafa;
		border: 1px solid #efefef;
		border-radius: 3px;
		color: #666;
		margin-top: 10px;
	}
</style>
