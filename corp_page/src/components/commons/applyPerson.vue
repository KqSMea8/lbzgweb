<template>
  <div class="relatedpersion">  
      <ul v-for="level in dataList" :class="{
        'custom-layer-gary': level.status == 0,
        'custom-layer-blue': level.status == 1,
        'custom-layer-green': level.status == 2,
        'custom-layer-red': level.status == 3 || level.status == 4
      }" class="relatedpersion-list">
        <template v-for="(item, i) in level.approvers">
          <li :class="{'relatedpersion-list-item':true}">
              <div class="status-ok">
                <img :src="item.header">
                <div class="relatedpersion-list-item-truename">{{item.trueName}}
                    <Icon class="success" v-if="item.status == 2" type="checkmark-circled"></Icon>
                    <Icon class="error" v-if="item.status == 3"  type="close-circled"></Icon>
                    <Icon class="error" v-if="item.status == 4"  type="close-circled"></Icon>
                </div>
              </div>
              <div class="apply-remark">
                <p class="apply-remark-title">审批意见：<span v-if="i === 0" class="apply-remark-level">{{getLevel(item.myLevel)}}级</span></p>
                <p>{{item.remark ? item.remark : '无'}}</p>
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
    },
    levelList: {
      type: Array
    }
  },
  data() {
    return {
      items:[],
      // 最后显示和加载的头像索引
      lastIndex:-1,
      dataList: []
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
    },
    levelList: function() {
      this.init(this.persons);
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
      let levelList = this.levelList
      for(var i=0;i<items.length;i++){
        items[i].status=1; //0.初始化 1.加载中 2.完成 3.失败
        // items[i].data = levelList[i]
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
            let dataList = levelList.map((item) => {
              item.approvers.map((approver) => {
                approver.header = items[approver.index].header
                approver.trueName = items[approver.index].trueName
              })
              return item
            })
            this.dataList = dataList

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
    },
    getLevel(val) {
      let level
      switch (val) {
        case 1:
          level = '一'
          break;
        case 2:
          level = '二'
          break;
        case 3:
          level = '三'
          break;
        case 4:
          level = '四'
          break;
        case 5:
          level = '五'
          break;
        case 6:
          level = '六'
          break;
        default:
          break;
      }
      return level
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
    border: 1px dashed #ccc;
    padding-right: 4px;
    padding-left: 0px;
    padding-bottom: 4px;
    margin-bottom: 10px;
  }
  .relatedpersion-list.custom-layer-green{
    border-color: #32b16c;
    background: #eff9f3;
  }
  .relatedpersion-list.custom-layer-red{
    border-color: #e35d5d;
    background: #fdf2f2;
  }
  .relatedpersion-list.custom-layer-blue{
    border-color: #91cbf7;
    background: #eef8ff;
  }
  .relatedpersion-list.custom-layer-gary{
    background: #fafafa;
  }

  .relatedpersion-list-item{
    text-align: left;
    overflow: hidden;
    cursor: default;
    margin:0px;
    margin-left: 4px;
    margin-top:4px;
    padding: 10px;
    border-radius: 5px;
  }
  .relatedpersion-list-item .error{
    color: #e35d5d;
  }
  .relatedpersion-list-item .success{
    color: #32b16c;
  }
  .relatedpersion-list-item .apply-remark{
    display: inline-block;
    line-height: 20px;
    vertical-align: top;
    padding: 10px 0 0 20px;
    font-size: 14px;
  }
  .relatedpersion-list-item .apply-remark-title{
    color: #999;
    width: 350px;
    position: relative;
  }
  .relatedpersion-list-item .apply-remark-level{
    position: absolute;
    right: 0;
  }
  .relatedpersion-list-item.hiden{
    display: none;
  }

  .relatedpersion-list-item .status-ok{display: inline-block; text-align: center;}

  .relatedpersion-list-item.status-ok .status-ok {
    display: inline-block;
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