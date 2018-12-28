<template>
<LayoutVer>
  <div slot="top">
  </div> 
  <LayoutHor :fix="true" :left="'contacts-layout-left'">
    <div slot="left" class="contacts-left">
       <LayoutVer>
          <div slot="top" class="contacts-top-title">物料种类</div> 
          <div class="contacts-dept-tree">
            <Category ref="category" 
              @on-select="selCate" 
              @on-menu-delete="delCate"
              @on-menu-add="addCate" 
              @on-menu-update="updateCate"></Dept>
            ></Category>
          </div>
      </LayoutVer> 
    </div> 
    <Material ref="material" class="contacts-member"></Material> 
  </LayoutHor>
</LayoutVer>
</template>

<script>

import LayoutVer from '@/components/layout/LayoutVer';
import LayoutHor from '@/components/layout/LayoutHor';
import Category from '@/components/material/Category';
import Material from '@/components/material/MaterialList';

export default {
  components: {
    LayoutVer,
    LayoutHor,
    Category,
    Material
  },
  data() { 
    return { 
      actionDept:0,
      actionEmp:0
    }
  },
  mounted(){  
    this.loadCategory();
  },
  computed:{ 
  },
  methods:{
    loadCategory() {
      this.$http.post('/api/material/category/list', {}).then((res) => {
        if (res.data.code === 0 && res.data.data !=null) {
          this.$refs.category.load(res.data.data);
        } else {
          this.$Message.error(res.data.message)
        }
      }).catch((error) => { 
        this.$Message.error(error.message)
      });
    },
    selCate(cur){
      this.$refs.material.queryForm.cateCode = cur.code;
      this.$refs.material.query();
    },
    addCate(editCate){
      let that = this;
      if(editCate.parentCode==0){
        editCate.parentCode="";
      }
      this.$http.post('/api/material/category/add', 
      {
        parentCode: editCate.parentCode, 
        title: editCate.title,
        code: editCate.code
      }).then((res) => {
        if (res.data.code === 0) {
          that.loadCategory();
        } else {
          this.$Message.error(res.data.message)
        }
      }).catch((error) => {
         this.$Message.error(error.message)
      });
    },
    updateCate(editCate){
      this.$http.post('/api/material/category/update',
       {
        title: editCate.title,
        code: editCate.code
      }).then((res) => {
        if (res.data.code === 0) {
          this.loadCategory();
        } else {
          this.$Message.error(res.data.message)
        }
      }).catch((error) => {
        this.$Message.error(error.message)
      });
    },
    delCate(code){ 
     this.$Modal.confirm({
        title: '删除确认',
        content: '<p>确定删除该分类吗？</p>',
        closable:true,
        onOk: () => {
          let that = this;
          this.$http.post('/api/material/category/delete?code=' + code).then((res) => {
            if (res.data.code === 0) {
              that.loadCategory();
            } else {
              this.$Message.error(res.data.message)
            }
          }).catch((error) => {
            this.$Message.error(error.message)
          });
        }
      });
    }
  }
}

</script>
<style type="text/css">
  /*部门树*/
  .contacts-left .dept-tree-node .tv-node.select{
    background-color: #e8f8fd;
    color: #20bfee;
  }
  .contacts-left .dept-tree-node .tv-node:hover {
    background-color: #e8f8fd;
  }

</style>

<style type="text/css">
  .contacts-bar-alert { 
    height: 42px;
    line-height: 24px;
    margin-bottom: 0px;
  }
  .contacts-left {
     height:100%;
     width:240px;
     background-color: white;
     border-right: 1px solid #eeeeee;
  }
  .contacts-top-title {
    line-height: 42px;
    font-size: 12px;
    padding-left:15px;
    border-bottom: 1px solid #eeeeee;
    /*background-color: #F9FAFB;*/
  }
  .contacts-left-footer {
    line-height: 40px;
    text-align: left;
    cursor: pointer;
    background-color: white;
  }

  .contacts-left-footer .footer-txt {
    display: block;
    color: #495060;
    border-top: 1px solid #eeeeee;
    padding-left: 15px;
    /*background-color: #F9FAFB;*/
  }
   .contacts-left-footer .footer-txt:hover {
    color: #20bfee;
    background-color: #e8f8fd;
    border-right:2px solid #20bfee; 
  }

  .contacts-left-footer .active {
    color:#20bfee;
    background-color: #e8f8fd;
    border-right:2px solid #20bfee; 
  }
  
  .contacts-layout-left.layout-hor-left{
    width: 240px;
  }
  
</style>