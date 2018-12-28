<template>
  <div :class="{'attachfile':1,'noselect':1,
                'attachfile-readonly':readonly}">
    <div :class="{'attachfile-body':1,
                 'display-1':display==1,
                 'display-2':display==2}">
      <div class="attachfile-list"> 
        <div class="attachfile-file" v-for="(file,index) in files" :key="index">
          <div class="attachfile-file-name" @click="view(file)">{{file.fileName}}.{{file.fileNameExt}}</div>
          <div class="attachfile-file-btns">
            <Icon @click.native="download(file)" class="下载" type="android-download"></Icon>
            <Icon @click.native="remove(file)" title="删除" type="close-round" class="icon-delete"></Icon>
          </div>
        </div>
        <div v-if="loading==0 &&　(files == null || files.length==0)" class="info-empty">暂无内容</div>
        <div v-if="loading==1" class="info-loading">加载中 ...</div>
      </div>
      <Upload name="file"
        v-if="!readonly"
        :show-upload-list="false" 
        :action="action"
        :on-success="onUpload">
        <Button type="ghost" icon="ios-cloud-upload-outline" size="small">上传文件</Button>
      </Upload>
    </div>
    <div v-if="false" class="attachfile-tabs" @click="display=2">
      <Icon type="arrow-swap"></Icon><br/>上<br/>传
    </div>
    <div v-if="false" class="attachfile-tabs" @click="display=1">
      <Icon type="arrow-swap"></Icon><br/>列<br/>表
    </div>
  </div>
</template>
<script>　
  export default {
    components: {
     　 
    },
    data() {　
      return {
        loading:0,
        display:1,//1.列表 2.上传
        files:[],
        action:this.$http.defaults.baseURL + '/api/file/upload',
        fileIds:'',
      }
    },
    mounted: function () {
      this.splitValue(this.value); 
      this.load();
    },
    computed: {

    },
    model: {
      prop: 'value',
      event: 'input-value'
    },
    props:{
      value:{
        type:String,
        default:''
      },
      readonly:{
        type:Boolean,
        default:false
      }
    },
    watch:{
      value(new_val,old_val){
        if(new_val != old_val){
          this.splitValue(new_val); 
          this.load();
        }
      }
    },
    methods: {
      splitValue(val){
        if(val != null && typeof(val) != 'undefined'){
          this.fileIds = val;
        }else{
          this.fileIds = '';
        }
      },
      load() { 
        this.loading = 1;  
        //查询角色列表数据
        this.$http.get("/api/file/list?fileIds="+this.fileIds).then((res) => {
          if (res.data.code === 0) {
            this.loading = 0;
            this.files = res.data.data;
          } else {
            this.loading = 0;
            this.files = [];
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.$Message.error("请求失败，请重新发送")
        }); 
      },
      view(item){
        window.open(this.$http.defaults.baseURL + '/api/file/view?fileId='+item.fileId);
      },
      download(item){
        window.open(this.$http.defaults.baseURL + '/api/file/download?fileId='+item.fileId);
      },
      remove(item){
        var vals = [];
        this.files.map((file)=>{
          if(file != item){
            vals.push(file.fileId);
          }
        });
        this.$emit('input-value',vals.join(','));
      },
      onUpload(response, file, fileList){
        var vals = [];
        (this.value?this.value:'').split(',').map((str)=>{
          if(str){
            vals.push(str);
          }
        });
        if(response.code == 0){
          vals.push(response.data);
        }
        this.$emit('input-value',vals.join(','));
      }
    }
  }

</script>

<style type="text/css">
  .attachfile{
    cursor: default;
    position: relative; 
    min-height: 100px;
    height: 100%;
    font-size: 14px;
  }
  .attachfile-body{
    overflow-y: auto;
    background-color: #fdfdfd;
    border: 1px solid #efefef;
    border-radius: 3px;
    height: 100%;
    width: 100%;
  }
  .attachfile-tabs{
    position: absolute;
    width: 24px;
    right: 0px;
    top: 10px;
    border: 1px solid #efefef;
    background-color: #fdfdfd;
    padding: 4px;
    cursor: pointer;
    border-left: 0px;
    border-radius: 0px 3px 3px 0px;
  }
  .attachfile-tabs:hover{
    color: #20bfee;
    background-color: #e8f8fd; 
  }
  .attachfile-file{
    height: 28px; 
    padding-right: 50px;
    text-align: left;
    position: relative;
  }
  .attachfile-file:hover .attachfile-file-name{
    color: #20bfee;
    background-color: #e8f8fd;
  }

  .attachfile-file-name{
    padding:0px 10px; 
    cursor: pointer;
    height: 100%;
    line-height: 28px;
  }

  .attachfile-file-btns{
    text-align: center;
    position:absolute;
    right: 10px; top:0px;
    line-height: 28px; 
  }

  .attachfile-file-btns .icon-delete{
    margin-left: 10px;
  }

  .attachfile-file-btns .icon-delete{
    color:#ed3f14;
    cursor: pointer;
  }
  .attachfile-file-btns .ivu-icon:hover{
    color:#20bfee;
    cursor: pointer;
  } 

  .attachfile .info-loading{
    margin:10px;
    text-align: center;
    color:#999;
  }
  .attachfile .info-empty{
    margin:10px;
    text-align: center;
    color:#999;
  }
  /*只读*/
  .attachfile-readonly .attachfile-tabs{
    display: none;
  }
  .attachfile-readonly.attachfile{
    padding-right: 0px;
  }
  .attachfile-readonly .icon-delete{
    display: none;
  } 
  .attachfile-readonly .attachfile-file{
    padding-right: 30px;
  }
  .attachfile .ivu-upload.ivu-upload-select{
    padding: 6px 10px; text-align: right;
  }
  .noselect{
    -moz-user-select: none; /*火狐*/
    -webkit-user-select: none;  /*webkit浏览器*/
    -ms-user-select: none;   /*IE10*/
    -khtml-user-select: none; /*早期浏览器*/
    user-select: none;
  }
</style>
