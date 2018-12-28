<template>
   <div class="page sysmgr-page">
    <Tabs :animated="false">
        <TabPane label="应用管理" name="appMgr" > 
          <table class="list">
            <tr class="list-row">
              <td class="list-row-title">应用管理员</td>
              <td>
                <div class="persons"> 
                  <template v-for="emp in personArr">
                    <Icon type="android-contact" class="icon-type"></Icon>{{emp.trueName}}
                  </template>
                  <a @click="selectPersons" v-if="personArr.length == 0 ">--</a>
                 </div>
              </td>
            </tr>
            <tr>
              <td></td>
              <td>
                <Button size="small" type="primary" @click="selectPersons(personArr)" v-if="personArr.length > 0 ">设置</Button>
              </td>
            </tr>
          </table>  
        </TabPane>
        <TabPane label="自定义角色" name="roleMgr" v-if="false">
            <div class="role-mgr">
              <div class="page-searchbox">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td>
                      <Button @click="createRole">新建角色</Button>
                    </td>
                    <td><Input v-model="keyword" placeholder="请输入角色名称"></Input></td>
                    <td>
                      <Button type="primary">查询</Button>
                    </td>
                  </tr>
                </table>
              </div>
              <Loading :loading="loading">
               <div class="page-datatable">
                  <i-table  @on-selection-change="select" :columns="columns" :data="list" ></i-table>
                  <div style="height:10px;"></div>
                  <!-- <Page ref="pagebar" :total="total" size="small"
                        @on-page-size-change="pageSizeChange" @on-change="pageChange" show-sizer placement="top" :transfer="true"></Page>   -->
                </div>
              </Loading>
          </div>
        </TabPane>
    </Tabs>
        <GroupCreate ref='group' @on-submit-group="load"></GroupCreate>
        <!--<Loading v-if="loading"></Loading>-->
        <SelectDeptPerson ref="selectDeptPerson"></SelectDeptPerson>
  </div>
</template>
<script>
  import DataRowOperateBar from '@/components/commons/DataRowOperateBar';
  import SelPersons from '@/components/workflow/SelPersons';
  import GroupCreate from '@/components/workflow/GroupCreate';
  import Loading from '@/components/loading';
  import SelectDeptPerson from '@/components/workflow/components/SelectDeptPerson';
  export default {
    components: {
      DataRowOperateBar,
      Loading,
      SelPersons,
      GroupCreate,
      SelectDeptPerson
    },
    data() {
      var that = this;
      return {
        persons:'renxin',
        personArr:[],
        total:0,
        list:[],
        keyword:'',
        show:false,
        title:'新建角色',
        isEdit:false,
        group:{
          id:'',
          title:'',
          empIds:''
        },
        columns:[
          {
            type: 'selection',
            width: 50,
            align: 'center'
          },
          {
            title: '角色名称',
            key: 'title'
          },
          {
            title: '分配人员',
            key: 'empNames',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'text',
                    size: 'small',
                    icon: 'edit'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.selPerson(params.row);
                    }
                  }
                }, params.row.empNames)
              ]);
            }
          },
          {
            title: '操作',
            width:100,
            align:'center',
            render:function(h, params) {
              return h(DataRowOperateBar,{
                props:{
                  commands:'编辑,删除'
                },
                on:{
                  'on-command': (name) =>{
                    that.rowCommand(name,params)
                  }
                }
              });
            }
          }
        ],
        selection:[],
        loading:0
      }
    },
    mounted () {
      this.load();
      this.query();
    },
    methods: {
       load(){
            this.loading = 1;
            this.$http.post('/api/wf/group/list',{}).then((res) => {
              if (res.data.code === 0) {
                  this.loading = 0;
                  this.list = res.data.data;
              } else {
                this.loading = 0;
                this.$Message.error(res.data.message);
              }
            }).catch((error) => {
              this.loading = 0;
              this.$Message.error(error.toString())
            })

       },
        select(selection){
          this.selection=selection;
        },
        rowCommand(name,params){
            if(name == '编辑'){
              this.updateRole(params.row.id);
            }
            if(name == '删除'){
              var id = params.row.id;
              this.$Modal.confirm({
              title: '删除确认',
              content: '<p>删除后不能恢复,确定要删除？</p>',
              onOk: () => {
                this.$http.post('/api/wf/group/delete?id=' + id, {}).then((res) => {
                  if (res.data.code === 0) {
                    this.$Message.success("删除成功");

                    this.load();
                  } else {
                    this.$Message.error(res.data.message)
                  }
                }).catch((error) => {
                    this.$Message.error(error.toString())
                });
             }
            });
            return;
          }
        },
       setPersons(emps){
        this.persons = emps;
       },
       createRole(){
        this.$refs.group.open();
       },
       updateRole(id){
         this.$refs.group.open(id);
       },
      query:function(){
        let empIds = this.persons
        this.$http.post('/api/contacts/getBaseInfo', {empIds}).then((res) => {
          if (res.data.code === 0) {
            let datas = res.data.data
            this.personArr = datas
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.toString())
        });
      },
      selectPersons(personArr){
         var pop = this.$refs.selectDeptPerson;

          pop.selectPerson = true;
          pop.selectDept = false;
          pop.isMulti = true;

          pop.$off('ok');
          pop.$on('ok',()=>{
            pop.$off('ok');
            this.persons = pop.select.map((person)=>{
                            return person.key;
                          }).join(',');
            this.personArr =  pop.select.map((person)=>{
                            let emp = {empId:person.key,trueName:person.title}
                            return emp;
                          });

          });
          pop.show();
          pop.select = this.innerGetDeptPerson(personArr);
       },
       selPerson(role){
          var pop = this.$refs.selectDeptPerson;

          pop.selectPerson = true;
          pop.selectDept = false;
          pop.isMulti = true;

          pop.$off('ok');
          pop.$on('ok',()=>{
            pop.$off('ok');
            var empIds =  pop.select.map((person)=>{
                            return person.key;
                          }).join(',');
            this.$http.post('/api/wf/group/update',{id:role.id,empIds:empIds}).then((res) => {
              if (res.data.code === 0) {
                  this.load();
                  this.$Message.info('设置人员成功');
              } else {
                  this.$Message.error(res.data.message);
              }
            }).catch((error) => {
                this.$Message.error(error.toString())
            })
          });
          pop.show();
          pop.select = this.innerGetDeptPerson(role.empList);
       },
      innerGetDeptPerson(field){
          var arr = [];
          var vals = field;
          if(vals == null){
            return arr;
          }
          for(var i=0;i<vals.length;i++){
             var item = {type:'',key:'',title:''};
             item.type = 'person';
             item.key = vals[i].empId;
             item.title = vals[i].trueName;
             arr.push(item);
          }
          return arr;
    }


    }
  }
</script>
<style type="text/css">
  .sysmgr-page .list{
    margin: 0px 10px;
    font-size: 14px;
  }
  .sysmgr-page .list td{
    line-height: 24px;
    padding-right: 5px;
  } 
  .sysmgr-page .list-row-title{
    color: #999; 
  }
  .sysmgr .persons{

  }
</style>
