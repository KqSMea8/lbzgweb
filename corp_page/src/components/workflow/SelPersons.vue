<template>
  <div class="selContacts" >
    <section v-for="(item, index) in items" class="layer-img" @click="del(index, item)" :ref="'layerImg' + index">
      <p class="layer-del" v-if="isSuper || isDel">
        <Icon type="trash-a"></Icon>
      </p>
      <img :src="item.header || defaultImg" class="user" v-if="item.header"/>
      <span class="ivu-avatar ivu-avatar-icon" v-else><i class="ivu-icon ivu-icon-person"></i></span>
      <span>
        {{item.trueName}}
      </span>
    </section>
    <Button @click="showSelect" class="btn-add"  v-if="isSuper || isDel"><Icon type="plus"></Icon></Button>
    <Modal v-model="showPer"
           title="选择人员"
        ><SelContacts ref="selContacts" @on-getcheck="getPersons"></SelContacts>
        <div slot="footer"></div>
    </Modal>
  </div>
</template>
<script>
  import SelContacts from '@/components/commons/SelContacts';

  export default {
    name:'SelPersons',
    components: {
      SelContacts
    },
    props: {
      persons:{
        type:String,
        default:''
      },
      isSuper: {
        type: Number,
        default: 0
      },
      isDel: {
        type: Boolean,
        default: true
      }
    },
    data() {
      return {
        showPer:false,
        show: false,
        emps:'',
        items: [],
        defaultImg: require('../../assets/img/icon-user.png')
      };
    },
    mounted(){
      this.load(this.persons);
    },
    watch:{
      persons:function(val,old){
        if(val != old && val.length > 0){
          this.emps = val
        }else{
          this.emps = old
        }
        this.load(this.emps);
      }
    },
    methods:{
      load(val){
        if(typeof(val) === 'undefined'){
          val = this.persons
        }
        this.emps = val
        this.query()
        this.$refs.selContacts.closeSelect()
      },
      showSelect(){
        this.showPer = true;
        this.$refs.selContacts.showSelect(this.emps)
      },
      getPersons(data){
        this.show = true;
        this.showPer = false;
        this.$emit("on-selected",data)
        this.load(data, true);
      },
      query:function(){
        let empIds = this.emps
        this.$http.post('/api/contacts/getBaseInfo', {empIds}).then((res) => {
          if (res.data.code === 0) {
            let datas = res.data.data
            this.items = datas
          } else {
            this.$Message.error(res.data.message)
          }
        }).catch((error) => {
          this.$Message.error(error.toString())
        });
      },
      del(Index, Item){
        let VM = this
        let Emps = VM.emps.split(',')
        let newEmps = []
        let layerImg = VM.$refs['layerImg' + Index][0]
        layerImg.className = 'layer-img active-out'
        newEmps = Emps.map(emp => {
          if(emp != Item.empId){
            return emp
          }
        }).filter(emp => emp)
        VM.emps = newEmps.join(',')
        setTimeout(() => {
          layerImg.className = 'layer-img'
          VM.items.splice(Index, 1)
        }, 200)
        VM.$emit("on-selected",this.emps)
        VM.$refs.selContacts.showSelect(newEmps.join(','))
        VM.$refs.selContacts.closeSelect()
      }
    }
  }

</script>

<style scoped>
  .layer-img{ display: inline-block;vertical-align: middle; text-align: center; width: 56px; margin-bottom: 15px; position: relative; cursor: pointer; transition: all 0.3s;}
  .layer-img.active-out{ transform: scale(0.5); opacity: 0; transform: rotateY;}
  .layer-img .user, .ivu-avatar{ width: 40px; height: 40px; line-height: 40px; display: block; border-radius: 50%; margin: 0 auto;}
  .btn-add{ vertical-align: top; width: 40px;  height: 40px;  border-radius: 50%;  font-size: 20px;  padding: 0;  color: #ccc; margin-left: 6px;}
  .layer-del{
    position: absolute;  width: 40px;  height: 40px;  top: 0;  left: 50%;  z-index: 5;  background: rgba(0,0,0,0.4);  margin-left: -20px;
    border-radius: 50%;  color: #fff;  display: flex;  align-items: center;  text-align: center; font-size: 20px; transition: all 0.4s; opacity: 0; visibility: hidden;
  }
  .layer-img:hover .layer-del{ opacity: 1; visibility: visible; transform: rotate(360deg);}
  .layer-del i{ flex: 1;}
</style>

