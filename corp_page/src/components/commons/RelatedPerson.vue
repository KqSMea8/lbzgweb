<template>
  <div class="relatedpersion">  
      <ul class="relatedpersion-list">
        <template v-for="item in items">
          <li :class="{'relatedpersion-list-item':true
          ,'hiden':!item.show
          ,'status-ok':item.status==2
          ,'status-loading':item.status==1
          ,'status-error':item.status==3}">
              <div class="status-ok">
                 <img :src="item.header">
                 <div class="relatedpersion-list-item-truename">{{item.trueName}}</div>
              </div>
              <div class="status-loading">
                <img :src="item.header" />
                <div class="relatedpersion-list-item-truename">加载中...</div>
              </div>
              <div class="status-error">
                <img :src="item.header" />
                <div class="relatedpersion-list-item-truename">加载出错</div>
              </div>
          </li>
        </template> 
      </ul>
      <div v-if="lastIndex + 1 < items.length " class="relatedpersion-more">
        <Button @click="load" type="text">加载更多 ({{items.length - lastIndex - 1}})</Button>
      </div>
  </div>
</template>
<script>


export default {
  name:'RelatedPersion',
  props:{
    persons:{
      type:String,
      default:''
    },
    pageSize:{
      type:Number,
      default:10
    }
  },
  data() {
    return {
      items:[],
      // 最后显示和加载的头像索引
      lastIndex:-1
    }
  },
  mounted:function(){
    this.init(this.persons);
  },
  computed:{
      
  },
  watch:{
    persons:function(val,old){ 
      if(val != old){
        this.init(val);
      }
    }
  },
  methods:{ 
    init:function(ids){
      if(typeof(ids) == 'undefined'){
        ids = this.persons;
      }
      var items = [];
      if(ids){
        var arr = ids.split(','); 　
        for(var i = 0;i<arr.length;i++){
           items.push({
              empId:arr[i],
              header:require('../../assets/img/icon-user.png'),
              trueName:'...',
              show:0, // 1.显示
              status:0 // 0.初始化 1.加载中 2.完成 3.失败
           });
        } 
      } 
      this.items = items;
      this.lastIndex = -1;
      this.load();
    },
    load:function(){

      var items = [];
      for(var i= 0;i<this.pageSize;i++){
        if(this.items.length>this.lastIndex+1){
          this.lastIndex++;
          var item = this.items[this.lastIndex];
          item.show = 1;
          item.status = 1;
          items.push(item);
        }
      }

      this.query(items);
    },
    query:function(items){
      var ids = [];
      for(var i=0;i<items.length;i++){
        items[i].status=1; //0.初始化 1.加载中 2.完成 3.失败
        ids.push(items[i].empId);
      }
      this.$http.post('/api/contacts/getBaseInfo', {empIds:ids.join(',')}).then((res) => {
          if (res.data.code === 0) {
            var rult = res.data.data;
            var rultMap = {}; 
            if(rult){
              for(var i=0;i<rult.length;i++){
                rultMap[rult[i].empId] = rult[i]; 
              }
            }
            for(var i=0;i<items.length;i++){
              items[i].status=2; //0.初始化 1.加载中 2.完成 3.失败
              var find = rultMap[items[i].empId];
              if(find){
                if(find.header){
                  items[i].header = find.header;
                } 
                items[i].trueName = find.trueName;
              }
            }

          } else { 
            this.$Message.error(res.data.message);
            for(var i=0;i<items.length;i++){
              items[i].status = 3;
            }
          }
        }).catch((error) => {
            this.$Message.error(error.toString());
            for(var i=0;i<items.length;i++){
              items[i].status = 3;
            }
        });
    }
  }
} 
</script>

<style type="text/css">
  .relatedpersion{
    width: 100%;
    background-color: #F9FAFB;
    min-height: 40px;
  } 

  .relatedpersion-list{
    padding-right: 4px;
    padding-left: 0px;
    padding-bottom: 4px;
  } 

  .relatedpersion-list-item{
    width: 70px;
    height: 70px;
    text-align: center;
    display: inline-block;
    
    overflow: hidden;
    cursor: default;
    margin:0px;
    margin-left: 4px;
    margin-top:4px;
  }
  .relatedpersion-list-item.hiden{
    display: none;
  }

  .relatedpersion-list-item .status-ok,
  .relatedpersion-list-item .status-loading,
  .relatedpersion-list-item .status-error{display: none;}

  .relatedpersion-list-item.status-ok .status-ok {
    display: inherit;
  }
  .relatedpersion-list-item.status-loading .status-loading {
    display: inherit;
  }
  .relatedpersion-list-item.status-error .status-error {
    display: inherit;
  }

  .relatedpersion-list-item img{
    width: 46px;
    height: 46px;
    background-color: white;
    border-radius:23px;
  }
  .relatedpersion-list-item-truename{
    white-space: nowrap;text-overflow:ellipsis; overflow:hidden;
    cursor: default;
  }

  .relatedpersion-more{
    text-align: center;
  }

</style>

