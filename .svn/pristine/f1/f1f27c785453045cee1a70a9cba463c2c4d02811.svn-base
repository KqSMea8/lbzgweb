<template>
  <div class="page home">
    <Row class="home-row">
        <Col span="5">
          <Card class="home-row-card home-row-card-corp" dis-hover>
            <table cellpadding="0" cellspacing="0" @click="goAccSet">
              <tr>
                <td class="home-row-card-corp-td1">&nbsp;</td>
              </tr>
              <tr>
                <td class="home-row-card-corp-td2">&nbsp;</td>
              </tr>
            </table>
            <img class="home-row-card-corp-img" @click="goAccSet" :src="corpLogo" />
            <div class="home-row-card-corp-title" @click="goAccSet">{{corpName}}</div>
          </Card>
        </Col>
        <Col span="10">
          <Card class="home-row-card home-row-card-avgtab" dis-hover>
            <table cellpadding="0" cellspacing="0">
              <tr>
                <td>总人数</td>
                <td rowspan="3"><div class="home-row-card-split"></div></td>
                <td>已关注</td>
              </tr>
              <tr>
                <td class="home-row-card-allperson">{{totalCount}}</td>
                <td class="home-row-card-attention">{{attention}}</td>
              </tr>
              <tr>
                <td class="home-row-card-allperson"><Icon type="person"></Icon></td>
                <td class="home-row-card-attention"><Icon type="person-add"></Icon></td>
              </tr>
            </table>
          </Card>
        </Col>
        <Col span="5">
          <Card class="home-row-card home-row-card-avgtab" dis-hover>
            <table cellpadding="0" cellspacing="0">
              <tr><td></td></tr>
              <tr><td class="home-row-card-user-normal"></td></tr>
              <tr>
                   
              </tr>
            </table>
          </Card>
        </Col>
        <Col span="4">
          <Card class="home-row-card home-row-card-avgtab" dis-hover>
            <table cellpadding="0" cellspacing="0">
              <tr><td></td></tr>
              <tr><td class="home-row-card-money"></td></tr>
              <tr><td>
                 
              </td></tr>
            </table>
          </Card>
        </Col> 
    </Row>
    <Card class="home-card home-notice" dis-hover>
      <p slot="title">
          <Icon type="volume-medium"></Icon>
          系统公告
      </p>
      <Button slot="extra" type="text">
        查看更多
      </Button>
    </Card>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      corpName:'',
      corpLogoRound:'',
      corpLogoQuare:'',
      notices:[],
      totalCount:0,
      attention:0
    }
  },
  mounted() {
    this.load(); 
    this.loadTotal();
    this.attendEmp();
  },
  computed:{
    corpLogo:function(){
      return this.corpLogoRound || this.corpLogoQuare || require('../assets/img/icon_corp.png');
    }
  },
  methods: {
    load:function () {
         
    },
    loadTotal:function(){
        this.$http.post('/api/contacts/emp/count', {
            dimission: 0,
            status: -1
          }).then((res) => {
            if (res.data.code === 0 && res.data.data!=null) {
              this.totalCount = res.data.data;
            } else {
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
            this.$Message.error(error.message)
        });
    },
    attendEmp:function(){
        this.$http.post('/api/contacts/emp/count', {
            dimission: 0,
            status: 1
          }).then((res) => {
            if (res.data.code === 0 && res.data.data!=null) {
              this.attention = res.data.data;
            } else {
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
            this.$Message.error(error.message)
        });
    },
    goAccSet:function(){
      if(!this.$user.isSuper){
          this.$Message.error('暂无权限！');
          return;
        }
      this.$router.push({name:'frm.accset'});
    }, 
    readDetail:function(id){
       this.$router.push({name:'frm.notice.view',query: {id:id}});
    }
  }
}
</script>
<style>
  .home {padding: 10px;}
  .home-row{margin: -4px;margin-bottom: 0px;}
  .home-row-card{
    height: 200px;
    /*height: 330px;*/
    margin: 4px;
    overflow: hidden;
  }
  .home-row-card.ivu-card,.home-card.ivu-card{border-radius: 0px;border-color: #f4f4f4;}
  .home-row-card .ivu-card-body,.home-card .ivu-card-body{height: 100%;padding: 0px;}
  .home-row-card-corp{
    position:relative;
    /*width: 370px;*/
  }
  .home-row-card-corp table{
    width: 100%;
    height: 100%;
  }
  .home-row-card-corp table td{
    height: 50%;
  }
  .home-row-card-corp-td1{
    background-color: #B9C7D9;
    /*height: 150px;*/
  }
  .home-row-card-corp-td2{
  }
  .home-row-card-corp-img{
    top:50%;
    left:50%;
    margin-left: -50px;
    margin-top: -50px;
    position:absolute;
    width: 100px;
    height: 100px;
    background-color: white;
    border-radius: 50px;
    border:1px solid #B9C7D9;
  }
  .home-row-card-corp-title{
    top:80%;
    width: 100%;
    font-size: 18px;
    color: #333;
    text-align: center;
    position:absolute;
    margin-top: -4px;
  }

  /**/
  .home-row-card-avgtab {
    /*width: 530px;*/
  }
  .home-row-card-avgtab table{
    width: 100%;
    height: 100%;
  }

  .home-row-card-avgtab table td{
    height: 33.33%;
    text-align: center;
  }
  .home-row-card-avgtab table{
    color: #999;
    font-size: 16px;
  }
  .home-row-card-split{
    width: 1px;
    /*height: 60%;*/
    /*height: 230px;*/
    height: 100%;
    border-right:solid 1px #efefef;
  }
  /**/
  td.home-row-card-attention{
    font-size: 30px;
    color:#37B36F;
  }

  td.home-row-card-allperson{
    font-size: 30px;
    color:#67686A;
  }

  td.home-row-card-user-normal{
    font-size: 24px;
    color:#67686A;
  }

  td.home-row-card-money{
    font-size: 24px;
    color:#f08c16;
  }
  /**/
  .home-notice{margin: 10px 0px 0px 0px;}
  .home-notice .ivu-card-head p{
    color: #999;
    font-size: 16px;
    padding-left: 16px;
  }
  .home-notice .ivu-card-extra span{
    color: #999;
  }
  .home-notice .ivu-card-extra span:hover{
    color: #20bfee;
  }
  .home-notice-list{width: 100%;}
  .home-notice-list td{
    height: 50px;
    cursor: pointer;
  }
  .home-notice-list .c1{
    padding: 0 31px;
    color: #333;
  }
  .home-notice-list .c2{width: 20%;text-align: right;padding: 0 31px;color: #999;}
  .home-notice-list tr:hover td{
    background-color: #e5f9fd;
  }
</style>


