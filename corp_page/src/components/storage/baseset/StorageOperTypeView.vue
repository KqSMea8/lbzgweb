<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page customerview">
      <Loading :loading="loading">
      <div class="page-form">
        <Form ref="form" :label-width="100"> 
          <FormItem label="库存操作代码">
            {{formItem.operId}}
          </FormItem>
          <FormItem label="库存操作名称" >
            {{formItem.name}}
          </FormItem> 
          <FormItem label="操作类型" >
            <span v-if="formItem.type==1">入</span>  
            <span v-if="formItem.type==2">出</span>
            <span v-if="formItem.type==3">移</span>
            <span v-if="formItem.type==4">调</span>
          </FormItem>
          <FormItem label="操作来源" >
            <span v-if="formItem.source==1">采购</span>   
            <span v-if="formItem.source==2">生产</span>
            <span v-if="formItem.source==3">库存</span>
            <span v-if="formItem.source==4">销售</span>
          </FormItem> 

          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          <Tag v-if="formItem.reject==1" color="green">退货</Tag>
          <Tag v-else>退货</Tag>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <Tag v-if="formItem.repair==1" color="green">返修</Tag>
          <Tag v-else>返修</Tag> 

          <FormItem>
            <Button type="primary" icon="checkmark" @click="close">确定</Button>
            <Button type="ghost" @click="edit" style="margin-left: 8px" >编辑</Button>
            <Button type="ghost" @click="close" style="margin-left: 8px">取消</Button>
          </FormItem>
        </Form>
      </div>
      </Loading>
    </div>
    <div slot="footer"></div>
  </Modal>
</template>
<script>
  import Loading from '@/components/loading';
  import areajs from '@/assets/js/area'

  export default {
    components: {
      Loading
    },
    data() {
      return {
        loading: 1,
        show: false, 
        
        //表单对象
        formItem: {
          operId:'',
          name:'',
          type:'',
          source:'',
          reject:'',
          repair:'',
        }
      }
    },
    computed: {
      title() {
        if (this.isEdit) {
          return "编辑库存类别信息";
        } else {
          return "查看库存类别信息";
        }
      }, 
    },
    mounted(){ 

    },
    methods: { 
      open(item) {
        this.show = true;
        Object.assign(this.formItem,item);
        this.loading = 0;
      },
      close() {
        this.show = false;
      },
      edit() {
        this.show = false;
        this.$emit('on-edit');
      } 
    }
  }

</script>

<style type="text/css">
</style>
