<template>
<Modal
    v-model="show"
    :title="title"
    :mask-closable="false"
    :closable="false"
    :width = "680"
    @on-ok="save"
    @on-cancel="cancel">
  <div class="page">
    <div class="page-content">
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
                      <table cellpadding="0" cellspacing="0" @click="">
                        <tr>
                            <td rowspan="2"><Icon type="clipboard" size="36"></Icon></td>
                            <td width="80%">数据表单</td>
                        </tr>                       
                        <tr>用于外部数据采集和管理</tr>
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
              </div>
            </div>
            <div class="form-have" v-if="showForm">
                <Table :columns="columns" :data="formList" @on-selection-change="select"></Table>
            </div>
    </div>
  </div>
</Modal>
</template>

<script>
    export default {
    components: {
    },
    data() {
      return {
        appId:0,
        show:false,
        showForm:false,
        showBlank:false,
        title:'新建表单',
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
          /*  {
                title: '所属人',
                key: 'age'
            },*/
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
                key: 'createTime',
                align: 'center'
            }
        ]
      }
    },
    mounted () {
    },
    methods: {
    	open: function(appId) {
        this.appId = appId;
	      this.show = true;
        this.showForm = false;
        this.showBlank = true;
        this.formList = [];
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
      select:function(selection){
        this.selection=selection;
      },
      cancel(){

      },
      save(){
        if(this.showForm){
           let ids = [];
           if (this.selection.length == 0) {
                return;
            }
           ids = this.selection.map((item) => {
                  return item.id
                })
           this.$http.post('/api/wf/form/setForm',{appId:this.appId,ids:ids}).then((res) => {
              if (res.data.code === 0) {
                   this.$Message.info('添加表单成功');  
                   this.$emit('on-form-create');                 
              } else {
                  this.$Message.error(res.data.message);
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
            });
        }else{
          this.$router.push({path:'/workflow/formdesign'});
        }

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
