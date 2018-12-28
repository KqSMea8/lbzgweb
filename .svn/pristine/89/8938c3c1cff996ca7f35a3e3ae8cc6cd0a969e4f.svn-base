<template v-if="show">
  <Modal v-model="show" :title="title ">
    <!--<Loading v-if="loading"></Loading>-->
    <Loading :loading="loading">
      <div class="page">
      <div class="page-form">
        <Form :label-width="70" ref="formData" :model="formData" >
          <FormItem label="流程名称" class="arc-form-item prop-label"  required>
            <Input v-model="formData.flowName" ref="title" size="small" placeholder="流程名称"></Input>
          </FormItem>

        </Form>
      </div>
      <div class="prop-content">
        <div class="prop-left">
          <span>表单数据</span>
            <div v-for="(item,index) in fields" :class="{'list-item':1}" @click="setFieldValue(item)">{{item.title}}</div>
        </div>
        <div  class="prop-right">
          <span>变量</span>
          <div>
            <div v-for="(item,index) in flowVar" :class="{'list-item':1}" @click="setFieldValue(item)">{{item.title}}</div>
          </div>
        </div>
      </div>
    </div>
    </Loading>
    <div slot="footer">
      <Button type="text" size="large" @click="show = false">取消</Button>
      <Button type="primary" size="large" @click="save">保存</Button>
    </div>
  </Modal>
</template>
<script>
  import Loading from '@/components/loading'

  export default {
    components: {Loading},
    props: {
      value: Boolean
    },
    watch: {},
    data() {
      return {
        title: '编辑流程标题',
        loading: 1,
        show: false,
        formData: {
          flowName: '',
          flowNameValue: '',
        },
        id: 0,
        formObj: {},
        fields: [],
        flowVar: [
          {'id': '当前登录人', 'title': '当前登录人'},
          {'id': '当前人员所在部门', 'title': '当前人员所在部门'},
          {'id': '当前登录人员所属兼职部门', 'title': '当前登录人员所属兼职部门'},
          {'id': '当前登陆人员的手机号', 'title': '当前登陆人员的手机号'},
          {'id': '当前人员所在单位', 'title': '当前人员所在单位'},
          {'id': '系统日期时间', 'title': '系统日期时间'},
          {'id': '系统日期', 'title': '系统日期'},
          {'id': '本月初日期', 'title': '本月初日期'},
          {'id': '本月末日期', 'title': '本月末日期'},
          {'id': '本年初日期', 'title': '本年初日期'},
          {'id': '本年末日期', 'title': '本年末日期'}
        ],
        formFields: {},
        fieldMap: {},
        fieldReverseMap: {}
      }
    },
    mounted() {
    },
    methods: {
      open(id) {
        this.id = id;
        this.show = true;
        this.loading = 1;
        this.getForm(id);

      },
      getForm(id){
        var that = this;
//      that.reportId = report;
        this.$http.post("/api/wf/form/get?id=" + id, {}).then((res) => {
          if (res.data.code === 0) {
            that.loading = 0;
            that.formObj = res.data.data;
            that.formFields = JSON.parse(res.data.data.fieldsJson);
            this.getFormFields();
            this.buildFieldMap();
            this.setFormTitle();
          } else {
            that.loading = 0;
            that.list = null;
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          console.log(error)
          this.$Message.error("请求失败，请重新发送")
        });
      },
      setFormTitle(){
        var that = this;
        var regex = /\{\{(.+?)\}\}/g;
        var title = that.formObj.flowTitle;
        var titleVar = title.match(regex);
        if (titleVar) {
          var map = that.fieldMap;
          titleVar.forEach(function (val, key) {

            val = val.replace('{{', '').replace('}}', '');
            var field = map.get(val);
            if (field) {
              var fieldTitle = field.title;
              title = title.replace(val, fieldTitle);
            }
          })
        }
        that.formData.flowName = title;
        that.formData.flowNameValue = that.formObj.flowTitle;
      },
      buildFieldMap(){
        //将formFields 构建成map
        var that = this;
        var formFields = that.formFields;

        //将datafields 转换成json 格式
        var fieldMap = new Map();
        var fieldReverseMap = new Map();
        formFields.forEach(function (field, key) {
          let id = field.id;
          fieldMap.set(id, field);
          fieldReverseMap.set(field.title, id);
        })
        that.fieldMap = fieldMap;
        that.fieldReverseMap = fieldReverseMap;
      },
      getFormFields(){
        var that = this;
        that.fields = [];
        let fieldsJson = JSON.parse(that.formObj.fieldsJson);
        fieldsJson.forEach(function(field,key) {
          var item = {};
          item.id = field.id;
          item.title = field.title;
          that.fields.push(item);
        })
      },
      setFieldValue(item){
        var that = this;
        if(!that.formData.flowName.includes('{{'+item.title+'}}')){
          that.formData.flowName = that.formData.flowName+'{{'+item.title+'}}';
          that.formData.flowNameValue = that.formData.flowNameValue+'{{'+item.id+'}}';
        }
      },
      save() {
        let saveApi = '/api/wf/form/updateFormProperty'
        if(!this.formData.flowNameValue.replace(/ /g,'')){
          this.$Message.error("流程标题不能为空")
          this.$refs.title.$refs.input.focus()
          return
        }else{
          this.show = false
        }
        var param = {};
        param.id = this.id;
        this.setFormValue();
        param.flowTitle = this.formData.flowNameValue;

        this.$http.post(saveApi, param).then((res) => {
          this.loading = 0
          if (res.data.code === 0) {
            this.$Message.success("标题设置成功");
            this.$emit('callBack',this.formData.flowName)
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.message)
        })
      },
      setFormValue(){
        var that = this;
        var regex = /\{\{(.+?)\}\}/g;
        var title = that.formData.flowName;
        var titleVar = title.match(regex);
        if(titleVar){
          var map = that.fieldReverseMap;
          titleVar.forEach(function(val,key) {
            val = val.replace('{{','').replace('}}','');
            var id = map.get(val);
            if(id){
//              var fieldTitle = field.title;
              title = title.replace(val, id);
            }
          })
        }
        that.formData.flowNameValue = title;
      },
    }
  }

</script>

<style scoped>
  .prop-label{
    text-align: left;
  }
  .ivu-form .ivu-form-item-label{
    text-align: left;
  }
  .prop-content{
    height: 300px;
  }
  .prop-left{
    width: 49%;
    height: 100%;
    float: left;
    border-right: 1px solid #eeeeee;
    overflow: hidden;
    /*padding: 0px 10px;*/
    font-size: 13px;
  }
  .prop-right{
    width: 50%;
    float: right;
    height: 100%;
    overflow: hidden;
    /*padding: 0px 10px;*/
    font-size: 13px;
  }
  .list-item{
    height: 25px;
    line-height: 25px;
    font-size: 12px;
    padding: 0px 10px;
    cursor: pointer;
  }

  .list-item:hover{
    color: #5990cf;
  }
</style>
