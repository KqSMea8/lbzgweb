<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page customerview">
      <Loading :loading="loading">
      <div class="page-form">
        <Form ref="form" :label-width="100"> 
          <FormItem label="仓库代码">
            {{formItem.storageId}}
          </FormItem>
          <FormItem label="仓库名称" >
            {{formItem.name}}
          </FormItem> 
          <FormItem label="所属部门" >
            {{formItem.departmentName}}
          </FormItem>
          <FormItem label="仓库类别" >
            {{formItem.typeName}}
          </FormItem>
          <FormItem label="联系人" >
            {{formItem.linkman}}
          </FormItem> 
          <FormItem label="仓库电话" >
            {{formItem.telphone}}
          </FormItem>
          <FormItem label="仓库地址" >
            {{formItem.address}}
          </FormItem>
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
          storageId:'',
          name:'',
          departmentName:'',
          typeName:'',
          linkman:'',
          telphone:'',
          address:'',
        }
      }
    },
    computed: {
      title() {
        if (this.isEdit) {
          return "编辑仓库信息";
        } else {
          return "查看仓库信息";
        }
      }, 
    },
    mounted(){ 

    },
    methods: { 
      open(item) {
        this.show = true;
        Object.assign(this.formItem,item);
        if(this.formItem.area){
          this.formItem.area = areajs.getText(this.formItem.area).split('|').join('  ');
        }
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
