<template>

  <div class="loginBox">
    <table >
      <tr>
        <td>企业编号</td>
        <td>
          <i-input size="small" :value.sync="corpId"></i-input>
        </td>
      </tr>
      <tr>
        <td>用户账号</td>
        <td><i-input size="small" :value.sync="userId"></i-input></td>
      </tr>
      <tr>
        <td></td>
        <td>
          <i-button size="small" type="primary"  @click="login">登录</i-button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      corpId:'wxdca02fc5f641dabb',
      userId:'renxin'
    }
  },
  methods: {
    login() {
      //bus.$emit('loginChange', true)
      this.$http.post('api/login/login', { 'corpId': this.corpId, 'userId': this.userId }).then((res) => {
        if (res.data.code === 0) {
          //to do
          this.$Message.info('ok');
        } else {
          this.$Message.error(res.data.message)
        }
      }).catch((error) => {
        this.$Message.error(error.toString())
      })
    }
  }
}
</script>
<style type="text/css">
  .loginBox{width:300px;margin:0px auto;margin-top: 100px;}
  .loginBox table td{padding: 4px;}
</style>
