<template>
  <div class="page storageauth">
    <div class="page-title">
      <a @click="goPage('/storage/baseset')">仓库基础设置</a> -&gt;仓库操作权限分配
    </div>
    <Row>
      <Col span="10">
        <div class="page-tools">
          <table cellpadding="0" cellspacing="0">
            <tr>
              <td><Button @click="addEmp" v-power="'ckgl.ckczqx.add'" icon="plus">添加</Button></td> <!--添加按钮addEmp方法中 实现弹出新增管理员选择界面 -->
              <td><Button @click="removeEmp" v-power="'ckgl.ckczqx.remove'" icon="plus">移除</Button></td>
            </tr>
          </table>
        </div>
        <div class="page-datatable">
          <i-table :columns="empColumns"
            @on-current-change="switchCurEmp"
            :data="emps" highlight-row></i-table> <!--仓库管理员列表 emps-->
        </div>
      </Col>
      <Col span="14">
        <div class="page-tools">
          <table cellpadding="0" cellspacing="0">
            <tr>
              <td><Button @click="save" v-power="'ckgl.ckczqx.save'" icon="plus">保存</Button></td>
              <td><Button @click="reset" v-power="'ckgl.ckczqx.refresh'" icon="plus">刷新</Button></td>
            </tr>
          </table>
        </div>
        <div class="page-datatable">
          <i-table :columns="storageColumns"
            @on-row-click="rowClick"
            :data="storages"></i-table>  <!--仓库列表 storages  -->
        </div>
      </Col>
    </Row>
    <SelContacts ref="edit"></SelContacts> <!-- 新加管理员控件 已经写好的，直接调用参数名即可-->
  </div>
</template>
<script> 　
  import SelContacts from '@/components/selcontacts'; //选择部门和人员----已经写好的控件，供项目公用

  export default {
    components: {
      SelContacts  //声明新组件
    },
    data() {
      return {
        empColumns:[
          {
            title: '用户代码',
            key: 'empId',
            width: 140,
            align: 'left'
          },
          {
            title: '用户名称',
            key: 'empName',
            //width: 120,
            align: 'left'
          },
        ],
        emps:[],//<!--仓库管理员列表 emps-->

        storageColumns:[
          {
              title: '授权',
              width: 60,
              align: 'center',
              key: '_checked',
              render(h,params){
                var row = params.row;
                return h('Icon',{
                  props:{
                    type:'android-checkbox' + (row._checked?'':'-outline-blank'),
                  },
                  class:'storageauth-check-icon'
                })
              }
          },
          {
            title: '仓库名称',
            key: 'name',
            //width: 120,
            align: 'left'
          },
        ],
        storages:[],//<!--仓库列表 storages  -->

        curEmpId:null, //一个临时变量： 当前员工id值
        // loading: 0
      }
    },
    mounted: function () {
      this.load();
      this.loadAuth();
    },
    computed: {},
    methods: {
      load(){
        this.$http.post("/api/storage/list", {}).then((res) => {
          if (res.data.code === 0) {
            var rows = res.data.data.rows;
            for (let i = 0; i < rows.length; i++) {
              const element = rows[i];
              element._checked=false;
            }
            this.storages = rows;
          } else {
            this.storages = null;
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.$Message.error("请求失败，请重新发送")
        });
        // 1.加载管理员
        this.loadEmps();
      },

      loadEmps(){
        //1.加载管理员
        this.$http.post("/api/storage/admin/list", {}).then((res) => {
          if (res.data.code === 0) {
            var rows = res.data.data;//.rows;
            this.emps = rows;
          } else {
            this.emps = null;
            this.$Message.error(res.data.message);
          }
        }).catch((error) => {
          this.$Message.error("请求失败，请重新发送")
        });
      },

      //重置授权仓库
      loadAuth(){
        if(this.curEmpId != null){//鼠标没有选中时，
          // this.$Message.error('请选择要移除的管理员');
          // return
          //	/api/storage/admin/getAuth?empId=
          // alert("选中某个管理员，显示对应的仓库授权点。"+this.curEmpId);
          var empId = this.curEmpId;
          this.$http.post('/api/storage/admin/getAuth?empId='+empId, {}).then((res) => {
          if (res.data.code === 0) {
            var rows = res.data.data;//.rows;
            var  ids=[];

            for (let i = 0; i < rows.length; i++) {
              const element = rows[i];
              // alert("该管理员已授权的仓库名: "+element.name);
              // alert(element._checked);
              ids.push(element.storageId);
            }
            for(var i=0;i<this.storages.length;i++){
                var storage=this.storages[i];
                storage._checked = ids.indexOf(storage.storageId) >=0;
            }
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.message)
        });
        }
      },

      //添加仓库管理员按钮的实现方法
      addEmp(){
        var sel = this.$refs.edit;//引用该控件
        sel.show({
          isMulti:false,
          selectPerson:true,
          selectDept:false,
        ok:()=>{
         if(sel.select.length>0){
           this.saveItem(sel.select[0].key);
          }
         }
        });
      },

    //传入员工id值,调用API进行DB保存；
     saveItem(empId1) {
        let url = '';
        let msg = '';
        url = '/api/storage/admin/add';
        msg = '添加成功';
        this.$http.post(url, {empId:empId1}).then((res) => {
          if (res.data.code === 0) {
             this.$Message.success(msg);
             this.loadEmps();// 重新加载管理员
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.message)
        });
      },

      //移除某个管理员
      removeEmp(){
        // 删除管理员后，重新刷新数据
        if(this.curEmpId == null){//鼠标没有选中时，
          this.$Message.error('请选择要移除的管理员');
          return
        }
        let url = '/api/storage/admin/delete?empId=';//要删除的哪个员工
        let msg = '移除成功';
        var empId = this.curEmpId;//当前仓库管理员的员工id

        this.$http.post(url+empId, {}).then((res) => {
          if (res.data.code === 0) {
             this.$Message.success(msg);
             this.loadEmps();// 重新刷新加载管理员
             this.curEmpId = null;
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.message)
        });
      },

      save(){
        if(this.curEmpId == null){
          this.$Message.error('请选择管理员');
          return
        }
        var storageIds = [];
        var empId = this.curEmpId;
        this.storages.map((st)=>{
          if(st._checked) storageIds.push(st.storageId); });
        // alert("仓库数组storageIds： "+storageIds.join(',')); //保存的是仓库的storageIds；
        // alert("该管理员empId："+empId);//保存的是管理员empId;
        let msg = '授权保存成功';

        this.$http.post('/api/storage/admin/setAuth', {empId:empId,storageIds:storageIds}).then((res) => {
          if (res.data.code === 0) {
            this.$Message.success(msg);
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.message)
        });
      },

      reset(){
        this.loadAuth();
      },
      rowClick(row,index){
        this.storages[index]._checked = !this.storages[index]._checked;
      },
      switchCurEmp(cur,old){
        this.curEmpId = cur.empId;
        this.loadAuth();
      },
      goPage(url){
        this.$router.push(url);
      }
    }

  }

</script>

<style type="text/css">
  .storageauth{
  }
  .storageauth-check-icon{
    font-size: 18px;
    color:#eb5954;
  }
  .storageauth-check-icon.ivu-icon-android-checkbox-outline-blank{
    color:#ccc;
  }
  .storageauth .ivu-table-row-highlight td{
    color:#eb5954;
  }
</style>
