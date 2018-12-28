<template> 
  <div class="page logininfo"> 
    <div class="page-bar">
      <Icon type="document"></Icon> 我的账号
    </div>
    <div>
      <Loading :loading="loading" :mask="mask">
      <Form :label-width="80" ref="form" :model="loginInfo" class="logininfo-from" :rules="ruleValidate" >
          <Form-item label="登录名" prop="loginName">

            <Row>
                <Col span="18">
                    {{loginInfo.loginName}}
                </Col>
                <Col span="4" offset="1">
                    <Button @click="edit()">修改</Button>
                </Col>
            </Row>

          </Form-item>

          <Form-item label="旧密码" prop="oldPwd">
            <Input type="password" placeholder=""  v-model="loginInfo.oldPwd"/>
          </Form-item>

          <Form-item label="新密码" prop="newPwd">
            <Input type="password" placeholder=""  v-model="loginInfo.newPwd"/>
          </Form-item>

          <Form-item label="确认密码" prop="confirmPwd">
            <Input type="password" placeholder=""  v-model="loginInfo.confirmPwd"/>
          </Form-item>

          <Form-item label=""> 
            <Button class="btn-save" type="primary" @click="save">确定</Button>   
          </Form-item>

      </Form>
      </Loading>
    </div>
    <div>
      <Loading v-if="loading"></Loading> 
    </div>
  </div>
</template>
<script>
import Loading from '@/components/loading';

export default {
  components: {
    Loading
  },
  data() { 
    var that = this;
    return { 
      loading:0,
      mask:1,
      showLoginName:0,
      loginName:'',
      loginInfo:{
        loginName:'',
        oldPwd:'',
        newPwd:'',
        confirmPwd:''
      },
      ruleValidate: {
          newPwd: [
              { required: true, message: '新密码不能为空', trigger: 'blur' }
          ],
          confirmPwd: [
              { required: true, message: '确认密码不能为空', trigger: 'blur' }
          ]
      }
    }
  },
  mounted:function(){ 
    this.load();
  },
  computed:{

  },
  methods:{
    load: function() {
      this.loading = 1;
      this.mask = 2;  
      this.$http.post("/api/login/getPwdInfo", {}).then((res) => {
        this.loading = 0;
        if (res.data.code === 0) {
          var data = res.data.data;
          this.loginName = data.loginName;
          this.loginInfo.loginName = data.loginName;
          if(data.hasPassword == 0){
            this.$Message.info("未设置旧密码，旧密码可为空");
          }
        }else{
          this.$Message.error(res.data.message)
        }
      }).catch((error) => {
          this.loading = 0;
          this.$Message.error("获取密码信息出错，请刷新页面")
      });
    },
    save:function(){ 
      this.$refs['form'].validate((valid) => {
          if (valid) {
              var that = this;
              var newPwd = that.loginInfo.newPwd;
              var confirmPwd = that.loginInfo.confirmPwd;
              if(newPwd != confirmPwd){
                that.$Message.error("新密码与确认密码不一致");
                return;
              }
              that.$http.post("/api/login/changePwd", that.loginInfo).then((res) => {
                that.loading = 0;
              if (res.data.code === 0) {
                that.$Message.info("密码修改成功");
                setTimeout(function(){
                  window.location.href="/corp";
                },1000)
              } else {
                that.$Message.error(res.data.message)
              }
              }).catch((error) => {
                that.loading = 0;
                that.$Message.error("修改密码出错，请刷新页面")
              });
          } else {
            return ;
          }
      });
    },
    saveLoginName:function(){
        var that = this;
        if(that.loginName == ''){
          this.$Message.error('登录名不能为空')
          return;
        }
        var data = {};
        data.loginName = that.loginName;
        that.$http.post("/api/login/changeLoginName", data).then((res) => {
          that.loading = 0;
          if (res.data.code === 0) {
            that.loginInfo.loginName = that.loginName;
            that.$Message.info("登录名修改成功");
            setTimeout(function(){
              window.location.href="/corp";
            },1000)
          } else {
            that.$Message.error(res.data.message)
          }
        }).catch((error) => {
          that.loading = 0;
          that.$Message.error("修改登录名出错，请刷新页面")
          });
    },
    edit:function(){
      this.$Modal.confirm({
        render: (h) => {
        return h('Input', {
          props: {
            value: this.loginInfo.loginName,
            autofocus: true,
            },
          on: {
              input:(val)=>{
                this.loginName = val;
              }
            }
          })
      },
      onOk: () => {
        if(this.loginName == ''){
          this.$Message.error('登录名不能为空')
          return;
        }
        this.saveLoginName();
        }
      })
    }
  }
}

</script>

<style type="text/css">
  
  .logininfo-from{
    width: 340px;
    margin: auto;
    margin-top: 40px;
  }

  .logininfo .btn-save{
    width: 100px;
  }

</style>
