<template>
  <Modal v-model="show" :title="title" :closable="false" :mask-closable="false">
    <div class="page customeredit">
      <Loading :loading="loading">
      <div class="page-form">
        <Form :model="formItem" ref="form" :label-width="100" :rules="ruleValidate" class="form-item">
          <div class="form-sub-title">
            <Icon type="compose"></Icon>
            基本资料
          </div>
          <FormItem label="仓库代码" prop="storageId">
            <Input v-model="formItem.storageId" placeholder="" :disabled="isEdit" class="width-1" />
          </FormItem>
          <FormItem label="仓库名称" prop="name">
            <Input v-model="formItem.name" placeholder="" class="width-1"/>
          </FormItem>

          <FormItem label="所属部门" prop="departmentName">
            <Input v-model="formItem.departmentName"  @on-click="selDept" placeholder="" class="width-1"  icon="search" />
          </FormItem>

          <FormItem label="仓库类别" prop="type"><!--下拉选择 -->
            <Select v-model="formItem.type" class="width-1" placeholder="仓库类别">
                <Option v-for="item in $args.argMap['warehouse_type']" :value="item.argCode" :key="item.argCode">{{ item.argText }}</Option>
            </Select>
          </FormItem>

          <FormItem label="联系人" prop="linkman">
             <Input v-model="formItem.linkmanName"  @on-click="selLinkman" placeholder="" class="width-1"   icon="search" />
          </FormItem>

          <FormItem label="仓库电话" prop="telphone">
            <Input v-model="formItem.telphone" placeholder="" class="width-1" />
          </FormItem>
          <FormItem label="仓库地址" prop="address">
            <Input v-model="formItem.address" placeholder="" :maxlength="100"/>
          </FormItem>
          <FormItem>
            <Button type="primary" icon="checkmark" @click="save">保存</Button>
            <Button type="ghost" @click="reset" style="margin-left: 8px" >重置</Button>
            <Button type="ghost" @click="close" style="margin-left: 8px">取消</Button>
          </FormItem>
        </Form>
      </div>
      </Loading>
       <SelContacts ref="selContacts"></SelContacts> <!-- 新加部门人员控件 -->
    </div>
    <div slot="footer"></div>
  </Modal>
</template>
<script>
  import Loading from '@/components/loading';
  import SelArea from '@/components/selarea';
  import SelContacts from '@/components/selcontacts'; //选择部门和人员----已经写好的控件，供项目公用

  export default {
    components: {
      Loading, SelArea, SelContacts  //声明新组件
    },
    data() {
      return {
        loading: 1,
        show: false,
        //是否编辑 0 添加 1 编辑
        isEdit: false,

        departmentName: [],//所属部门
        // typeName: [],//类型名称

        //表单对象
        formItem: {
          storageId:'',
          name:'',
          department:'',
          departmentName:'',
          type:'',
          linkman:'',
          linkmanName:'',
          telphone:'',
          address:'',
        },
        oriItem:{
        },
        //验证
        ruleValidate: {
          storageId: [
            {required: true, whitespace: true, message: '编码不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
          name: [
            {required: true, whitespace: true, message: '名称不能为空', trigger: 'blur'},
            {type: 'string', max: 50, message: '不能超过50个字', trigger: 'blur'}
          ],
        }
      }
    },
    computed: {
      title() {
        if (this.isEdit) {
          return "编辑仓库信息";
        } else {
          return "新建仓库信息";
        }
      },
    },
    mounted(){
      this.loadGroups()
    },
    methods: {
      save() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            this.saveItem();
          } else {
            return;
          }
        });
      },
      
      //选择联系人
      selLinkman(){
        var sel = this.$refs.selContacts;//引用该控件
        sel.show({
          isMulti:false,
          selectPerson:true,
          selectDept:false,
        ok:()=>{
         if(sel.select.length>0){
            this.formItem.linkmanName = sel.select[0].title;//
            this.formItem.linkman = sel.select[0].key;
          }
         }
        });
      },

    //选择部门
    selDept(){
      // alert("选择部门。。");
      var sel = this.$refs.selContacts;
      sel.show({
       isMulti:false,
       selectPerson:false,
       selectDept:true,
       ok:()=>{ 
        if(sel.select.length>0){
          this.formItem.departmentName = sel.select[0].title;
          this.formItem.department = sel.select[0].key;
        }
      }
    });
  },

      saveItem() {
        let url = '';
        let msg = '';
        if (this.isEdit) {
          url = '/api/storage/update';
          msg = '修改成功';
        } else {
          url = '/api/storage/add';
          msg = '添加成功';
        }
        this.loading = 1;
        this.$http.post(url, this.formItem).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.$Message.success(msg);
            this.close();
            this.$emit("on-save");
            
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error(error.message)
        });
      },


      open(item) {
        this.show = true;
        this.$refs['form'].resetFields();

        if (item.storageId && item.storageId !== '') {
          this.isEdit = true;
          this.oriItem = item;
        } else {
          this.isEdit = false;
          this.oriItem = Object.assign({
            storageId:'',
            name:'',
            departmentName:'',
            type:'',
            linkman:'',
            telphone:'',
            address:'',},item);
        }
        Object.assign(this.formItem,this.oriItem);
      },
      close() {
        this.show = false;
      },
      reset() {
        $.extend(this.formItem,this.oriItem);
      },
      loadGroups() {
        this.loading = 1;
        //查询角色列表数据
        this.$http.post("/api/arg/list", {argGroup:'departmentName'}).then((res) => {
          this.loading = 0;
          if (res.data.code === 0) {
            this.industry = res.data.data;
          } else {
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.loading = 0;
          this.$Message.error("请求失败，请重新发送")
        });
      },
    }
  }

</script>

<style type="text/css">
  .form-sub-title {
    font-size: 14px;
    margin: 0 0 12px 18px;
    color: #2b85e4;
  }

  .customeredit .width-1{
    width: 160px;
  }
</style>
