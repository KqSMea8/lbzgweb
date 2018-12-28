var power = {}
power.defalut = {
  id:0,
  key:'key',
  title:'菜单',
  view:'',
  uri:'',
  iconfont:'navigate',
  icon:null,
};

power.defaultIconFont = 'navigate';

power.items =
[
  {id:2,key:'workflow',title:'工作流',view:'',uri:'', iconfont:'paper-airplane',parent:0},
  {id:201,key:'',title:'我的事项',view:'',uri:'/workflow/mywork',parent:2},
  {id:202,key:'',title:'我的应用',view:'',uri:'/workflow/myapp',parent:2},
  {id:203,key:'',title:'应用管理',view:'',uri:'/workflow/appmgr',parent:2},
  {id:204,key:'',title:'系统管理',view:'',uri:'/workflow/sysmgr',parent:2},
  {id:3,key:'normalsetting',title:'系统设置',view:'',uri:'',iconfont:'settings',icon:'',parent:0},
  {id:301,key:'',title:'组织架构',view:'',uri:'/contacts',iconfont:'navigate',parent:3},
  {id:302,key:'',title:'用户管理',view:'',uri:'/admin',iconfont:'navigate',parent:3},
  {id:303,key:'',title:'数据字典',view:'',uri:'/arg',iconfont:'navigate', parent:3 },
  {id:305,key:'',title:'物料编码',view:'',uri:'/material',   iconfont:'navigate',parent:3 },
  {id:304,key:'',title:'系统信息',view:'',uri:'/sysinfo',iconfont:'navigate', parent:3 },
  {id:4,key:'',title:'销售合同',view:'',uri:'',iconfont:'briefcase',icon:'',parent:0 },
  {id:401,key:'',title:'客户',view:'',uri:'/customer',iconfont:'',icon:'',parent:4},
  {id:402,key:'',title:'询价单',view:'',uri:'/inquire',iconfont:'',icon:'',parent:4},
  {id:403,key:'',title:'合同',view:'',uri:'/contract',iconfont:'',icon:'',parent:4},


  {id:5,key:'',title:'采购管理',view:'',uri:'',iconfont:'',icon:'',parent:0},
  {id:501,key:'',title:'供应商',view:'',uri:'/provider',iconfont:'',icon:'',parent:5},
  {id:502,key:'',title:'供应商名录',view:'',uri:'/provider-material',iconfont:'',icon:'',parent:5},
  {id:503,key:'',title:'新材料',view:'',uri:'/provider-newmaterial',iconfont:'',icon:'',parent:5},
  {id:503,key:'',title:'新材料供应管理',view:'',uri:'/provider-newmaterial',iconfont:'',icon:'',parent:5},
  {id:504,key:'',title:'采购订单',view:'',uri:'/purchase-order',iconfont:'',icon:'',parent:5},
  {id:505,key:'',title:'采购订单维护',view:'',uri:'/purchase-order-mgr',iconfont:'',icon:'',parent:5},
  {id:506,key:'',title:'询价结果登记',view:'',uri:'/provider-inquire',iconfont:'',icon:'',parent:5},

  {id:507,key:'',title:'调价申请',view:'',uri:'/provider-adjustprice',iconfont:'',icon:'',parent:5},

  {id:508,key:'',title:'入库驳回记录',view:'',uri:'/purchase-instockreject',iconfont:'',icon:'',parent:5},
  {id:509,key:'',title:'报检驳回记录',view:'',uri:'/purchase-inspectionreject',iconfont:'',icon:'',parent:5},
  {id:510,key:'',title:'付款申请',view:'',uri:'/provider-payapply',iconfont:'',icon:'',parent:5},


  {id:6,key:'',title:'仓库管理',view:'',uri:'',iconfont:'home',icon:'',parent:0},
  {id:601,key:'',title:'仓库设置',view:'',uri:'/storage/baseset',iconfont:'',icon:'',parent:6},
  {id:601,key:'',title:'仓库基础设置',view:'',uri:'/storage/baseset',iconfont:'',icon:'',parent:6},
  {id:602,key:'',title:'仓库管理',view:'',uri:'/storage/mgr',iconfont:'',icon:'',parent:6},
  {id:603,key:'',title:'仓库查询',view:'',uri:'/storage/query',iconfont:'',icon:'',parent:6},
  {id:604,key:'',title:'产成品库',view:'',uri:'/productstock/mgr',iconfont:'',icon:'',parent:6},


  {id:603,key:'',title:'产成品入库',view:'',uri:'',iconfont:'',icon:'',parent:6},
  {id:604,key:'',title:'产成品出库',view:'',uri:'',iconfont:'',icon:'',parent:6},

  {id:7,key:'',title:'工艺设计',view:'',uri:'',iconfont:'compose',icon:'',parent:0},
  {id:701,key:'',title:'基础设置',view:'',uri: '/basics/Index',iconfont:'',icon:'',parent:7},
  {id:703,key:'',title:'新材料试用记录',view:'',uri:'/new-material/Index',iconfont:'',icon:'',parent:7},
  {id:704,key:'',title:'合同铸件号维护',view:'',uri:'/contract-castings/Index',iconfont:'',icon:'',parent:7},
  {id:705,key:'',title:'合同工艺制作',view:'',uri:'/contract-design/Index',iconfont:'',icon:'',parent:7},

  {id:8,key:'',title:'质量管理',view:'',uri:'',iconfont:'eye',icon:'',parent:0},
  {id:801,key:'',title:'计量器具管理',view:'',uri:'/device',iconfont:'eye',icon:'',parent:8},
  {id:801,key:'',title:'合格证管理',view:'',uri:'/certificate/index',iconfont:'eye',icon:'',parent:8},
  {id:802,key:'',title:'检验记录管理',view:'',uri:'/inspectionRecord',iconfont:'happy',icon:'',parent:8},
  {id:804,key:'',title:'补做通知单管理',view:'',uri:'/quality/quality-redo/redo',iconfont:'eye',icon:'',parent:8},
  {id:805,key:'',title:'质量查询报表',view:'',uri:'/quality/quality-revision/Index',iconfont:'eye',icon:'',parent:8},


  {id:9,key:'',title:'外委加工',view:'',uri:'',iconfont:'planet',icon:'',parent:0},
  {id:10,key:'',title:'售后服务',view:'',uri:'',iconfont:'happy',icon:'',parent:0},
  {id:11,key:'',title:'车间生产',view:'',uri:'',iconfont:'nuclear',icon:'',parent:0}
];

power.navs = [];
power.build = function() {
  power.navs = [];

  this.items.map((item)=>{
    if((item.iconfont || item.icon || '') == ''){
      item.iconfont = power.defaultIconFont;
    }
    if(item.parent == 0){
      power.navs.push(item);
      item.subItems = [];
      buildSubItems(item);
    }
  })
}
function buildSubItems(nav) {
  power.items.map((item)=>{
    if(item.parent == nav.id){
      nav.subItems.push(item);
      item.subItems = [];
    }
  })
}

power.build();

power.rebuild = function(items){
  //{"powerId":10,"powerKey":"xtzs","powerType":1,"powerIcon":"","powerCaption":"系统设置","powerUrl":"","isShow":1,"parentId":0,"seq":1,"creator":0,"createTime":"2018-11-12 16:10:51","lastUpdate":"2018-11-12 16:10:51"}
  //{id:11,key:'',title:'车间生产',view:'',uri:'',iconfont:'nuclear',icon:'',parent:0}
  power.items = [];
  items.map((item)=>{
    if(item.isShow && item.powerType != 3){
      power.items.push({
        id:item.powerId,
        key:item.powerKey,
        title:item.powerCaption,
        view:'',
        uri:item.powerUrl,
        iconfont:item.powerIcon,
        icon:'',
        parent:item.parentId,
        hasPower:true
      });
    }
  });
  power.build();
}

power.each = function(callback){
  each(power.navs,callback);
}

function each(items,callback){
  if(items){
    items.map((item)=>{
      if(callback(item) == false){
        return ;
      }
      each(item.subItems,callback);
    })
  }
}

export default power;
