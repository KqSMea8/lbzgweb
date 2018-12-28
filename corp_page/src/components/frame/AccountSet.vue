<template>
  <div class="page accset">
    <div class="page-bar"><Icon type="document"></Icon> 账号设置</div>
    <div class="accset-title">账号信息</div>
    <div class="accset-box">
      <table class="accset-box-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="accset-box-table-label">账号名称</td>
          <td>{{corp.corpId}}</td>
          <td class="accset-box-table-operate"><Button type="text"></Button></td>
        </tr>
        <tr>
          <td class="accset-box-table-label">账号类型</td>
          <td>普通用户</td>
          <td class="accset-box-table-operate"><Button type="text"></Button></td>
        </tr>
        <tr>
          <td class="accset-box-table-label">账号余额</td>
          <td>0.00元</td>
          <td class="accset-box-table-operate"><Button type="text"></Button></td>
        </tr>
      </table>
    </div>
    <div class="accset-title"><span class="accset-title-padd">企业信息</span><Button  type="primary"@click="syncCorpInfo"><Icon type="android-sync"></Icon> 同步</Button></div>
    <div class="accset-box">
      <table class="accset-box-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="accset-box-table-label">公司名称</td>
          <td>{{corp.corpName}}</td>
          <td class="accset-box-table-operate"></td>
        </tr>
        <tr>
          <td class="accset-box-table-label">圆形Logo</td>
          <td><img :src="corp.corpRoundLogoUrl" style="width:60px"/></td>
          <td class="accset-box-table-operate"><Button type="text"></Button></td>
        </tr>
        <tr>
          <td class="accset-box-table-label">方形Logo</td>
          <td><img :src="corp.corpQuareLogoUrl" style="width:150px"/></td>
          <td class="accset-box-table-operate"><Button type="text"></Button></td>
        </tr>
      </table>
    </div>
    <div class="accset-title">管理员信息</div>
    <div class="accset-box">
      <table class="accset-box-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="accset-box-table-label">管理员账户</td>
          <td v-if="!showName">{{corp.adminName}}</td>
          <td v-if="showName"><Input size="small" v-model="newAdmin.adminName"></Input></td>
          <td class="accset-box-table-operate"></td>
        </tr>
      </table>
    </div>
    <div class="accset-title">联系信息</div>
    <div class="accset-box">
      <table class="accset-box-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="accset-box-table-label">联系人</td>
          <td v-if="!showLinkMan">{{corp.linkMan}}</td>
          <td v-if="showLinkMan"><Input size="small" v-model="newCorp.linkMan"></Input></td>
          <td v-if="!showLinkMan" class="accset-box-table-operate"><Button type="text" @click="update('linkMan')">修改</Button></td>
          <td v-if="!showLinkMan" class="accset-box-table-operate"></td>

          <td  v-if="showLinkMan" class="accset-box-table-operate"><Button type="text" @click="update('linkMan')">保存</Button></td>
          <td  v-if="showLinkMan" class="accset-box-table-operate"><Button type="text" @click="update('linkMan',0)">取消</Button></td>
        </tr>
        <tr>
          <td class="accset-box-table-label">手机号</td>
          <td v-if="!showLinkMobile">{{corp.linkMobile}}</td>
          <td v-if="showLinkMobile"><Input size="small" v-model="newCorp.linkMobile"></Input></td>
          <td  v-if="!showLinkMobile" class="accset-box-table-operate"><Button type="text" @click="update('linkMobile')">修改</Button></td>
          <td  v-if="!showLinkMobile" class="accset-box-table-operate"></td>

          <td  v-if="showLinkMobile" class="accset-box-table-operate"><Button type="text" @click="update('linkMobile')">保存</Button></td>
          <td  v-if="showLinkMobile" class="accset-box-table-operate"><Button type="text" @click="update('linkMobile',0)">取消</Button></td>
        </tr>
      </table>
    </div>
    <div class="accset-title"></div>
    <div class="accset-box">
      <table class="accset-box-table" cellspacing="0" cellpadding="0">
        <tr>
          <td class="accset-box-table-label">账号注销</td>
          <td > 注销后，腾云帐号、腾云通讯录以及后台所有模块内容记录都将被删除，该操作不可撤销，请谨慎操作。 如有需要，建议提前备份好通讯录和相关数据！</td>
          <td class="accset-box-table-operate"><Button type="text" @click="clear()">注销账号</Button></td>
          <td class="accset-box-table-operate"></td>
        </tr>
      </table>
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
      showName:0,
      showLinkMobile:0,
      showLinkMan:0,
      isSure:false,
      click:0,
      corp:{
        corpId:'',
        corpName:'',
        corpShortName:'',
        description:'',
        isAuth:0,
        label:'',
        scale:'',
        status:0,
        linkMan:'',
        linkMobile:''
      },
      newCorp:{
        name:'',
        mobile:''
      },
      newAdmin:{
        adminName:''
      }
    }
  },
  mounted:function(){
    this.get();
  },
  computed:{

  },
  methods:{
    load: function() {

    },
    get: function(type){
      var that = this;
      that.$http.post("/api/corp/get").then((res) => {
        if (res.data.code === 0) {
          that.corp = res.data.data;
          if(type == 'man'){
            that.newCorp.linkMan = that.corp.linkMan;
          }else if(type == 'mobile'){
            that.newCorp.linkMobile = that.corp.linkMobile;
          }
          that.newAdmin.adminName = that.corp.adminName;
        } else {
          that.$Message.error(res.data.message)
        }
      }).catch((error) => {
        that.$Message.error("获取企业信息出错，请刷新页面")
      });
    },
    syncCorpInfo: function(){
      var that = this;
      that.loading = 1;
      that.$http.get("/api/contacts/refreshCorp").then((res) => {
        that.loading = 0;
        if (res.data.code === 0) {
          that.$Message.info("刷新企业信息成功");
          location.reload();
        } else {
          that.$Message.error(res.data.message)
        }
      }).catch((error) => {
        that.loading = 0;
        that.$Message.error("刷新企业信息出错，请刷新页面")
      });
    },
    save: function(type){
      var that = this;
      that.loading = 1;
      if(that.showLinkMobile == 1){
          var newMobile = that.newCorp.linkMobile;
          var mobile = that.corp.linkMobile;
          if(newMobile){
            var reg = /^1\d{10}$/;
            if(!reg.test(newMobile)){
              that.$Message.error("手机号输入有误");
              that.loading = 0;
              return;
            }
          }
      }
        var data = {};
        if(type == 'man'){
          data.linkMan = that.newCorp.linkMan;
          that.showLinkMan = 0;
        }else if(type == 'mobile'){
          data.linkMobile = that.newCorp.linkMobile
          that.showLinkMobile = 0;
        }
        that.$http.post("/api/corp/edit", data).then((res) => {
          that.loading = 0;
          if (res.data.code === 0) {
            that.$Message.info("修改企业联系人成功");
            that.get(type)
          } else {
            that.$Message.error(res.data.message)
          }
        }).catch((error) => {
          that.loading = 0;
          that.$Message.error("修改企业联系人出错，请刷新页面")
        });
    },
    update: function (title,type){
      var that = this;
      if(title == 'linkMan'){
        if(that.showLinkMan == 0){
          that.newCorp.linkMan = that.corp.linkMan;
          that.showLinkMan = 1;
        }else{
          if(type == 0){
            that.showLinkMan =0;
          }else{
            that.save('man');
          }
        }
      }else if(title == 'linkMobile'){
        if(that.showLinkMobile == 0){
          that.newCorp.linkMobile = that.corp.linkMobile;
          that.showLinkMobile = 1;
        }else{
          if(type == 0){
            that.showLinkMobile =0;
          }else{
            that.save('mobile');
          }
        }
      }
    },
    clear:function(){
    var that= this ;
    that.click=0;
    that.isSure = false;
    that.$Modal.confirm({
        loading:'loading',
        render: function (h)  {
        return h('div', {
                'class': ''
            }, [
                h('div',{
                  style: {
                    marginLeft:'6px'
                  }
                },'注销后，腾云帐号、腾云通讯录以及后台所有模块内容记录都将被删除，该操作不可撤销，请谨慎操作。 如有需要，建议提前备份好通讯录和相关数据！'),

                h('Checkbox', {
                  props: {
                    size:'default'
                    },
                  style: {
                      marginLeft:'5px',
                      // color:'red'
                    },
                  on: {
                      input:(val)=>{
                        that.isSure = val;
                        if(that.click==1){
                          this.onOk();
                        }
                      }
                    }
                  },'确认注销'),
            ])
      },
      onOk: () => {
          var isSure = that.isSure;
          that.click = 1;
          if(isSure){
            that.loading = 1;
            that.$http.post("/api/corp/clear", {}).then((res) => {
                var code = res.data.code;
                if(code === 0){
                    that.loading = 0;
                    window.location.href="/corp";
                }else {
                  that.loading = 0;

                  that.$Message.error({
                    content:'帐号内有未取消授权的应用，无法注销帐号，请先到<a href="https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome" target="_blank">企业微信后台</a>取消腾云应用授权。',
                    duration: 8,
                    closable: true
                    });
                  that.$Modal.remove();
                }
              }).catch((error) => {
                that.loading = 0;
              });
          }else{
            that.$Message.error("请选中确认注销");
          }
        }
      });

    }
  }
}

</script>

<style type="text/css">
  /*通用样式*/
  .page{
    padding: 10px;
  }
  .page-bar{
    height: 42;
    line-height: 41px;
    padding: 0px 10px;
    margin: -10px -10px 0px -10px;
    font-size: 14px;
    border-bottom: 1px solid #eee;
    cursor: default;
    background-color: #F9FAFB;
  }
  .accset{

  }
  .accset-title{
    font-size: 14px;
    margin: 4px 0px;
    margin-top: 20px;
  }
  .accset-box{
    border:1px solid #eee;
    padding: 4px;
  }
  .accset-box-table{
    width: 100%;
    margin: 0px 14px;
  }
  .accset-box-table-label{
    width: 120px;
    color: #999;
  }
  .accset-box-table-operate{
    width: 120px;
    text-align: center;
  }
  .accset-box-table td{
    height: 40px;
  }
  .accset-box-table tr td{
    border-bottom: 1px solid #eee;
  }

  .accset-box-table tr:last-child td{
    border-bottom: none;
  }
  .accset-title-padd{
    padding-right:10px
  }
</style>
