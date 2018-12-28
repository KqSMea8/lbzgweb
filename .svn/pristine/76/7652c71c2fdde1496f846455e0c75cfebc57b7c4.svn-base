<template>
    <div class="page">
      <div class="page-bar">
          <table class="page-bar-layout" cellspacing="0" cellpadding="0">
            <tr>
              <td class="page-bar-left">
                <Button size="small" icon="chevron-left" @click="goBack" type="warning">返回</Button>
              </td>
              <td class="page-bar-title">同步成员信息</td>
              <td class="page-bar-right"></td>
            </tr>
          </table>
      </div>

      <div class="sync-page-content">
        <br/>
        <p>注意事项：</p>
        <p>1、同步成员信息将会同步微信企业号通讯录；</p>
        <p>2、仅同步应用可见范围内组织架构中的成员信息；</p>
        <p>3、同步信息仅包含姓名、头像、性别、职位、部门、关注状态。</p>
        <p>&nbsp;</p>
        <Alert type="warning">
          <span v-if="syncInfo.syncStatus==1">同步中 <Spin size="small" style="display: inline-block;"></Spin></span>
          <span v-else>[{{syncInfo.endTime}}] - {{syncInfo.execResult}}</span> 
        </Alert>
        <p class="sync-btn">
          <Button type="primary" size="large" @click="sync" v-if="syncInfo.syncStatus!=1">开始同步</Button>
        </p> 
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
    return {
      syncInfo:{
        "threadId": 0,
        "execResult": "同步用户信息0条，同步部门0条。",
        "useTime": 3,
        "startTime": "",
        "endTime": "",
        "syncStatus": 0
      },
      timer:null
    }
  },
  mounted(){
    this.getContactSyncStatus();
  },
  computed:{

  },
  methods:{ 
    goBack(){
        this.$router.back(-1);
    },
    sync(){ 
      this.$http.post('/api/contacts/sync',{}).then((res) => {
          if (res.data.code === 0) {
            this.getContactSyncStatus();
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.toString())
      });
    },
    getContactSyncStatus(){  
      clearTimeout(this.timer);
      this.$http.post('/api/contacts/getSyncStatus',{
      }).then((res) => {
        if (res.data.code == 0) {
          Object.assign(this.syncInfo,res.data.data); 
          this.timer = setTimeout(this.getContactSyncStatus,2000);
        } else {
          this.syncInfo.syncStatus = 0;
          this.syncInfo.execResult = res.data.message;
        }
      }).catch((error) => {
        this.syncInfo.syncStatus = 0;
        this.syncInfo.execResult = res.data.message;
      });
    }
  }
}

</script>

<style type="text/css">
  .sync-page-content{
    width:900px;
    margin:0px auto;
  }
  .sync-btn{
    text-align: center;
  }
</style>

