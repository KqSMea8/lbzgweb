<template>
  <Modal
    v-model="show"
    :title="title"
    :mask-closable="false"
    :closable="false">
    <div class="admin-edit-page">
      <Loading :loading="loading" :mask="mask">
      <div class="page-form">
         <Form ref="form" :model="item" :rules="ruleValidate" :label-width="120">
            <FormItem label="登录账号" prop="loginName">
              <Input  v-model="item.loginName" placeholder="推荐使用邮箱、手机号，用于登录管理平台"></Input>
            </FormItem>
            <FormItem label="名称" prop="nickName">
              <Input  v-model="item.nickName" placeholder="用户昵称"></Input>
            </FormItem>
            <FormItem label="绑定通讯录用户" >
                <Input  v-model="item.empId" placeholder="输入绑定的员工编号"
                @on-click="selEmp" readonly="readonly" icon="search"></Input>
            </FormItem>
            <FormItem label="选择角色">
            <div>
                <Checkbox :indeterminate="indeterminate"
                  :value="checkAll"
                  @click.prevent.native="handleCheckAll">全选
                </Checkbox>
                <CheckboxGroup v-model="checked" @on-change="checkAllGroupChange">
                  <Checkbox v-for="(item, index) in checkData" :label="item.roleId" :key="'checkData' + index">
                      <span>{{item.roleName}}</span>
                  </Checkbox>
                </CheckboxGroup>
           </div>
           </FormItem>
            <FormItem id="clear">
                <Button  type="primary" icon="checkmark" @click="save()">保存</Button>
                <Button  type="default" @click="reset()" style="margin-left: 8px">重置</Button>
                <Button  type="default" @click="cancel()" style="margin-left: 8px">取消</Button>
            </FormItem>
        </Form>
      </div>
      </Loading>
    </div>
    <div slot="footer">

    </div>
    <SelContacts ref="selContacts"></SelContacts>
  </Modal>
</template>
<script>
import Loading from '@/components/loading';
import SelContacts from '@/components/selcontacts'; 

export default {
  components: {
    Loading,
    SelContacts
  },
  data() {
    var that = this;
    return {
      typeId:null,
      loading:1,
      mask:1,
      show:false,
      title:"子账号 - 编辑",
      item:{
        userId:0,
        loginName:'',
        nickName:'',
        empId:'',
        roleIds:''
      },
      checkAllGroup: [],
      indeterminate: true,
      checkAll: false,
      checked:[],
      checkData: [],
      ruleValidate: {
        loginName:[
            { required: true, message: '登录账号不能为空', trigger: 'blur' },
        ],
        nickName:[
            { required: true, message: '昵称不能为空', trigger: 'blur' },
          ]
      }
    }
  },
  mounted:function(){

  },
  methods:{
    handleCheckAll () {
      if (this.indeterminate) {
          this.checkAll = false;
      } else {
          this.checkAll = !this.checkAll;
      }
      this.indeterminate = false;
      if (this.checkAll) {
          this.checked = this.checkAllGroup;
      } else {
          this.checked = [];
      }
    },
    //改变时的样式
    checkAllGroupChange (data) {
        if (data.length === this.checkData.length) {
            this.indeterminate = false;
            this.checkAll = true;
        } else if (data.length > 0) {
            this.indeterminate = true;
            this.checkAll = false;
        } else {
            this.indeterminate = false;
            this.checkAll = false;
        }
    },
    open: function(id) {
      this.show = true;
      this.mask = 2;
      this.loading = 1;
      this.item.userId = id;
      this.loadRole();
    },
    load:function(){
      // 查询数据
      var that = this;
      that.loading  = 1;
      this.$refs['form'].resetFields();
      this.checked=[];
      this.$http.post('/api/admin/get?id=' + this.item.userId, {}).then((res) => {
        if (res.data.code === 0) {
           this.loading = 0;
           this.item = res.data.data;
           var role_ids = this.item.roleIds.split(",");
           for(var i=0;i<role_ids.length;i++){
              if(role_ids[i] != '' && role_ids[i] != null){
                that.checked.push(parseInt(role_ids[i]));
              }
           }
        } else {
          this.$Message.error(res.data.message)
        }
      }).catch((error) => {
        this.$Message.error(error.toString())
      });
    },
   loadRole: function() {
      this.checkData=[];
      this.$http.post('/api/role/list', {}).then((res) => {
          if (res.data.code === 0) {
            this.checkData = res.data.data.rows;
            var checkData1 = this.checkData;
            for(var i=0;i<checkData1.length;i++){//全部角色id
              this.checkAllGroup.push(checkData1[i].roleId);
            }
            this.load();
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.toString())
        });
    },
    save:function(){
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.loading = 1;
          this.mask = 1;
          this.item.roleIds =this.checked.join(',');
          this.adminEdit(this.item);
        } else {
          return ;
        }
      });
    },
    adminEdit(formItem){
      this.$http.post('/api/admin/edit', {
        userId:formItem.userId,
        loginName:formItem.loginName,
        nickName:formItem.nickName,
        empId:formItem.empId,
        roleIds:formItem.roleIds
      }).then((res) => {
        if (res.data.code === 0) {
          this.$Message.success("编辑子账号成功");
          this.loading =0;
          this.show = false;
          this.$emit("on-submit");
        } else {
          this.loading = 0;
          this.show = true;
          this.$Message.error(res.data.message);
        }
      }).catch((error) => {
          this.$Message.error(error.toString())
      });
    },
    selEmp(){
      var sel = this.$refs.selContacts;
      sel.show({
        isMulti:false,
        selectPerson:true,
        selectDept:false,
        ok:()=>{ 
          if(sel.select.length>0){ 
            this.item.empId = sel.select[0].key;
          }
        }
      });
    },
    reset:function(){
      this.mask = 1;
      this.load();
    },
    cancel:function(){
      this.show = false;
      this.$emit('on-cancel');
    }
  }
}

</script>

<style type="text/css">

</style>
