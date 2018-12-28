<template>
<Modal
    v-model="show"
    :title="title"
    :mask-closable="false"
    :closable="false"
    :width = "680">
  <div class="page">
    <div class="page-content">
        <Tabs v-model="name" :animated="false">
	        <TabPane label="基本信息" name="appCreateName">
            <Form ref="formItem" :model="formItem" :label-width="100" :rules="ruleValidate">
			        <FormItem label="应用名称" prop="title">
			            <Input v-model="formItem.title" placeholder="不超过30个字符"></Input>
			        </FormItem>
			         <FormItem label="应用描述" prop="description">
			            <Input v-model="formItem.description"  type="textarea" :rows="4" placeholder="不超过200个字符"></Input>
			        </FormItem>
			        <FormItem>
			            <Button type="primary" @click="next">下一步</Button>
			        </FormItem>
			      </Form>
	        </TabPane>
	        <TabPane label="创建表单" name="formCreateName">
            <div>
                <Row class="form-row" :gutter="32">
                  <Col span="12">
                    <Card class="form-row-card" :class="{'select-form-blank':showBlank}" dis-hover> 
                      <table cellpadding="0" cellspacing="0" @click="showBlankSel">
                        <tr>
                          <td rowspan="2"><Icon type="document" size="42"></Icon></td>
                          <td width="80%">空白表单</td>
                        </tr>
                        <tr><td>进入后可创建全新表单</td></tr>
                      </table>
                    </Card>
                  </Col>
                  <Col span="12">
                    <Card class="form-row-card" dis-hover :class="{'select-form-blank':showForm}"> 
                      <table cellpadding="0" cellspacing="0" @click="showFormSel">
                        <tr>
                            <td rowspan="2"><Icon type="clipboard" size="36"></Icon></td>
                            <td width="80%">选择已有表单</td>
                        </tr>                       
                        <tr>从已创建的表单中选择</tr>
                      </table>
                    </Card>
                  </Col>
                </Row>
              <div class = "form-select-content">
                  <div class="form-blank" v-if="showBlank">
                    <Row class="form-row" :gutter="16">
                      <Col span="8">
                        <Card class="form-row-card" dis-hover :class="{'select-form-blank':formType==0}"> 
                          <table cellpadding="0" cellspacing="0" @click="selFormType(0)">
                              <tr><td><Icon type="ios-paper" size="28"></Icon></td></tr>
                              <tr><td>瀑布式表单</td></tr>
                              <tr><td>每行只显示一个控件的表单</td></tr>
                          </table>
                        </Card>
                      </Col>
                      <Col span="8">
                        <Card class="form-row-card" dis-hover :class="{'select-form-blank':formType==1}"> 
                          <table cellpadding="0" cellspacing="0" @click="selFormType(1)">
                                <tr><td><Icon type="ios-grid-view" size="28"></Icon></td></tr> 
                                <tr><td>表格式表单</td></tr> 
                                <tr><td>一种类似excel的表单</td></tr>                      
                          </table>
                        </Card>
                      </Col>
                    </Row>
                  </div>
                  <div class="form-have" v-if="showForm">
                     <Table :columns="columns" :data="formList" @on-selection-change="select"></Table>
                  </div>
              </div>
            </div>
          </TabPane>
        </Tabs>
    </div>
  </div>
  <div slot="footer">
      <Button type="text" @click="cancel">取消</Button>    
      <Button type="primary" @click="save">保存</Button> 
  </div>
</Modal>
</template>

<script>
    export default {
    components: {
    },
    data() {
      return {
        show:false,
        showForm:false,
        showBlank:false,
        title:'新建应用',
        name:'appCreateName',
        formItem:{
           title:'',
           description:''
        },
        ruleValidate: {
          title: [
              { required: true,whitespace: true, message: '应用名称不能为空', trigger: 'blur' },
              { type: 'string', max: 30, message: '不能超过30个字', trigger: 'blur' }
          ],
          description: [
              { type: 'string', max: 30, message: '不能超过200个字', trigger: 'blur' }
          ]
        },
        formList:[],
        selection:[],
        formType:0, //0瀑布式 1表格式
        columns:[
            {
                type: 'selection',
                width: 60,
                align: 'center'
            },
            {
                title: '表单名称',
                key: 'title'
            },
            {
                title: '标识',
                key: 'isOpen',
                align: 'center',
                render: (h,params) =>{
                  if(params.row.isOpen == 1){
                    return '开启';
                  }
                  return '未开启';
                }
            },
            {
                title: '创建时间',
                align: 'center',
                key: 'createTime'
            }
        ]
      }
    },
    mounted () {
    },
    methods: {
    	open: function() {
        this.$refs['formItem'].resetFields();
	      this.show = true;
	      this.name = 'appCreateName';
        this.showForm = false;
        this.showBlank = true;
        this.formList = [];
	    },
	    goBack(){
	      this.$router.push({name:'workflow.appmgr'});
	    },
	    next(){
         this.$refs['formItem'].validate((valid) => {
          if (valid) {
            this.name = 'formCreateName';            
          }else{
            return ;
          }
         });	    	
	    },
      save(){
         this.$refs['formItem'].validate((valid) => {
            if (valid) {
               this.createApp();
            }else{
              this.name = 'appCreateName'; 
              return ;
            }
         });
      },
      showBlankSel(){
        this.showBlank = true;
        this.showForm = false;
      },
      showFormSel(){
        this.showBlank = false;
        this.showForm = true;
        this.loadFormList();
      },
      selFormType(type){
        this.formType = type;       
      },
      select:function(selection){
        this.selection=selection;
      },
      loadFormList(){
        this.$http.post('/api/wf/form/list',{appId:0}).then((res) => {
          if (res.data.code === 0) {
               this.formList = res.data.data;
          } else {
                this.$Message.error(res.data.message);
          }
        }).catch((error) => {
            this.$Message.error(error.toString())
        });
      },
      createApp(){
        this.$http.post('/api/wf/app/create', this.formItem).then((res) => {
            if (res.data.code === 0) {
                  this.show = false;
                  this.$Message.success("添加成功");
                  if(this.showForm){
                     this.setForm(res.data.data);
                  }else{
                    this.$emit("on-submit-app",res.data.data);
                  }               
            } else {
                  this.show = true;
                  this.$Message.error(res.data.message);
            }
          }).catch((error) => {
              this.$Message.error(error.toString())
          });
      },
      setForm(appId){
         let ids = [];
           if (this.selection.length == 0) {
                return;
            }
           ids = this.selection.map((item) => {
                  return item.id
                })
           this.$http.post('/api/wf/form/setForm',{appId:appId,ids:ids}).then((res) => {
              if (res.data.code === 0) {
                   this.$Message.info('添加表单成功');  
                   this.$emit('on-form-create');                 
              } else {
                  this.$Message.error(res.data.message);
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
            });
      },
      cancel(){        
        this.$refs['formItem'].resetFields();
        this.show = false;
      }
	}
}
</script>

<style type="text/css" scoped>
   .form-row{}
   .form-row .form-row-card{cursor: pointer;}
   .form-row .form-row-card table{height: 100%;width: 100%}
   .form-row table td{text-align: left;}
   .form-select-content{margin-top: 10px;}
   .form-select-content table td {text-align: center;font-size: 12px;}

   .select-form-blank{color: #20bfee;border-color:#20bfee; }
   .form-row-card:hover{color: #20bfee;border-color:#20bfee; }
</style>
<style>
   
</style>
