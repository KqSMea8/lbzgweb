<template>
  <div class="page inquirelist">
  <div class="layout">
    <Layout class="layout">
      <Sider hide-trigger style="background-color: #f9fafc;padding-top: 20px;padding-left: 5px">
        <Tree :data="TreeData" @on-select-change="treeClick"></Tree>
      </Sider>
      <Layout>

       <Content v-if="Treeindex == 'tjfx'">
         <TYJX ref="tjfx"></TYJX>
       </Content>
        <Content v-if="Treeindex == 'xnzb'">
          <Xnzb ref="xnzb"></Xnzb>
        </Content>
        <Content v-if="Treeindex == 'cc'">
          <Cc ref="cc"></Cc>
        </Content>
      </Layout>
    </Layout>
  </div>
  </div>
</template>
<script>
  import TYJX from './subset/newItem';
  import Xnzb from './subset/performance';
  import Cc from './subset/basic-size';
  export default {
    components: {
      TYJX,Xnzb,Cc
    },
    data () {
      return {
        TreeData: [
          {
            title: '技术参数',
            expand: true,
            children: [
              {
                title: '\n' +
                        'HX  化学成分',
                expand: true,
                children: [
                  {
                    title: '添加分项',
                    key:'tjfx'
                  }
                ]
              },
              {
                title: 'XN  性能指标',
                expand: true,
                children: [
                  {
                    title: '性能指标一',
                    key:'xnzb'
                  }
                ]
              },
              {
                title: 'CC  尺寸',
                expand: true,
                children: [
                  {
                    title: '尺寸一',
                    key:'cc'
                  }
                ]
              }
            ]
          }
        ],
        Treeindex:'tjfx'
      }
    },
    methods:{
      treeClick(x){
        console.log(x);
        var tree = x[0];
        if(!tree){
          return;
        }
        if(tree['key'] != ''){
          this.Treeindex = tree['key'];
        }
      }
    }
  }
</script>
