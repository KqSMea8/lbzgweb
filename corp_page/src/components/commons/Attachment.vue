<template>
  <div class="attach">
    <table class="attach-table" cellspacing="0" cellpadding="0">
        <tr  v-for="item in items">
          <td><img :src="getFileIcon(item.fileNameExt)" class="attach-img"></td>
          <td class="attach-filename">
            {{item.fileName}}.{{item.fileNameExt}}</td>
          <td class="attach-size">{{Math.ceil(parseFloat(item.fileSize)/1024)}}KB</td>
          <td class="attach-operation">
            <a download target="_blank" :href="'/corp/api/file/download?fileId='+item.fileId">
              <!-- <Button type="text" class="attach-operation-down" size="small">下载</Button> -->
              <Icon type="ios-download-outline" class="attach-operation-down"></Icon>
            </a>
          </td>
          <td class="attach-operation">
            <a target="_blank" :href="'/corp/api/file/view?fileId='+item.fileId">
            <!-- <Button type="text" class="attach-operation-view" size="small">预览</Button> -->
            <Icon type="ios-search-strong" class="attach-operation-view"></Icon></a>
          </td>
        </tr>
    </table>
    <div class="attach-loading" v-if="loading">
      <Spin></Spin>
    </div>
  </div>
</template>
<script>

  export default {
    name: 'Attachment',
    props: {
      queryCondition: {
        type: Object,
        default: null
        /*{
             module:'worklog',
             objId:''
        }*/
      }
    },
    data() {
      return {
        items: [],
        condition: null,
        loading: 0
      }
    },
    mounted: function () {
      this.init(this.queryCondition);
    },
    computed: {
      fileSize: function(val){
        return Math.round(parseFloat(val)) / 1024;
      }
    },
    watch: {
      queryCondition: function (val, old) {
        if (val != old) {
          this.init(val);
        }
      }
    },
    methods: {
      aa:function(val){
        alert(val);
      },
      getFileIcon: function(fileNameExt) {
         let fileType = 'other.png';
         if (['xls', 'xlsx','pdf','ppt', 'pptx','txt','doc', 'docx'].includes(fileNameExt)) {
            fileType = 'doc.png'
          } else if (['png', 'bmp', 'gif', 'jpg', 'jpeg'].includes(fileNameExt)) {
            fileType = 'img.png'
          } else if (['rar', 'zip','7z'].includes(fileNameExt)){
            fileType = 'zip.png'
          }
          return require('../../assets/img/filetype/' + fileType);
      },
      init: function () {
        this.condition = this.queryCondition;
        this.lastReplyId = null;
        this.items = [];
        this.load();
      },
      load: function () {
        var that = this;
        if (that.condition === null) {
          return;
        }
        that.loading = 1;
        that.$http.post('/api/file/getListByObj', that.condition).then((res) => {
          if (res.data.code === 0) {
            that.loading = 0;
            that.items = res.data.data;
          } else {
            that.loading = 0;
            that.$Message.error(res.data.message)
          }
        }).catch((error) => {
          that.loading = 0;
          that.$Message.error(error)
        });
      }
    }
  }

</script>

<style type="text/css">
  .attach {
    width: 100%;
    padding: 4px;
    background-color: #F9FAFB;
  }
  .attach-loading-loading {
    margin: auto;
    width: 40px;
    height: 40px;
  }
  .attach-img {
    width:32px;
    margin:6px;
    display:block;
  }
  .attach-filename {
    width: 100%;
    border-bottom: 1px dashed #ddd;
  }
  .attach-table {
    width: 100%
  }
  .attach-size{
    width:1%;
    padding: 0px 10px;
    color: #ccc;
    border-bottom: 1px dashed #ddd;
  }  
  .attach-operation{
    width:1%;
    border-bottom: 1px dashed #ddd;
  }
  .attach-operation-down,.attach-operation-view {
     color: #495060;
     font-size: 14px;
     margin-left: 10px;
  }
  
</style>

