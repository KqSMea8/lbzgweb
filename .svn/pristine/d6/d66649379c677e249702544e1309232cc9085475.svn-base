<template>
  <Modal
    v-model="show"
    :title="title"
    :mask-closable="false"
    :closable="false">
    <div class="admin-add-page">
      <Loading :loading="loading" :mask="mask">
      <div class="page-form">
         <Form ref="form" :model="item" :rules="ruleValidate" :label-width="100">
            <FormItem label="登录账号" prop="loginName">
                <Input  v-model="item.loginName" placeholder="推荐使用邮箱、手机号，用于登录管理系统"></Input>
            </FormItem>
            <FormItem label="名称" prop="nickName">
                <Input  v-model="item.nickName" placeholder="昵称"></Input>
            </FormItem>
            <FormItem label="新密码"  prop="password">
                <Input  v-model="item.password"  type="password" placeholder="输入新密码"></Input>
            </FormItem>
            <FormItem label="确认密码" prop="finalPassword">
                <Input  v-model="item.finalPassword" type="password" placeholder="输入确认密码"></Input>
            </FormItem>
            <FormItem label="绑定通讯录用户"  prop="empId">
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
            <Button type="primary" @click="save()">保存</Button>
            <Button @click="cancel()" style="margin-left: 15px">取消</Button>
           </FormItem>
        </Form>
      </div>
      </Loading>
    </div>
    <SelContacts ref="selContacts"></SelContacts>
    <div slot="footer">
    </div>
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
      loading:1,
      mask:2,
      show:false,
      title:"子账号 - 新增",
      item:{
        userId:0,
        nickName:'',
        loginName:'',
        password:'',
        finalPassword:'',
        roleIds:'',
        empId:''
      },
      checkAllGroup: [],
      indeterminate: false,
      checkAll: false,
      checked:[],
      checkData: [],
      ruleValidate: {
          nickName:[
            {required:true,message:'名称不能为空',trigger:'blur'},
          ],
          loginName:[
            { required: true, message: '登录账号不能为空', trigger: 'blur' },
          ],
          password: [
              { required: true, message: '密码不能为空', trigger: 'blur' },
              {type: 'string', min:6,max: 16, message: '6~16个字符，区分大小写', trigger: 'blur' }
          ],
          finalPassword: [
              { required: true, message: '确认密码不能为空', trigger: 'blur' },
              {type: 'string', min:6, max: 16, message: '6~16个字符，区分大小写', trigger: 'blur' }
          ]

      }
    }
  },
  mounted:function(){

  },
  methods:{
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
      //检查全选的样式
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
    open: function() {
      this.loading = 1;
      this.$refs['form'].resetFields();
      this.show = true;
      this.checkData=[];
      Object.assign(this.item,{userId:0,
        nickName:'',
        loginName:'',
        password:'',
        finalPassword:'',
        roleIds:'',
        empId:''});
      this.indeterminate= false;
      this.checkAll= false;
      this.checked=[];
      this.load();
    },
    load: function() {
      this.$http.post('/api/role/list', {
        page:1,
        pageSize:1000
      }).then((res) => {
         if (res.data.code === 0) {
            this.loading = 0;
            this.checkData = res.data.data.rows;
            var checkData1 = this.checkData;
            for(var i=0;i<checkData1.length;i++){//全部角色id
              this.checkAllGroup.push(checkData1[i].roleId);
            }
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
          this.item.roleIds =this.checked.join(',');
          if(this.item.password != this.item.finalPassword){
              this.$Message.info("密码不一致，重新输入");
              return;
          }
          this.addAdmin(this.item);
        } else {
          return ;
        }
      });
    },
    addAdmin(item){
      this.mask = 1;
      this.loading = 1;
      this.$http.post('/api/admin/add', {
        loginName:item.loginName,
        password:item.password,
        empId:item.empId,
        roleIds:item.roleIds,
        nickName:item.nickName
      }).then((res) => {
        this.loading =0;
        if (res.data.code === 0) {
          this.show = false;
          this.$Message.success("添加成功");
          this.$refs['form'].resetFields();
          this.$emit("on-submit");
        } else {
          this.loading = 0;
          this.show = true;
          this.$Message.error(res.data.message);
        }
      }).catch((error) => {
        this.loading =0;
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
      this.$refs['form'].resetFields();
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
