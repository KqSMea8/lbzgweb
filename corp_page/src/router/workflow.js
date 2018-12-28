let components = []

components.push({ component: 'workflow/MyWork',name:'workflow.mywork',path: '/workflow/mywork'});
components.push({ component: 'workflow/MyApp',name:'workflow.myapp',path: '/workflow/myapp'});
components.push({ component: 'workflow/SysMgr',name:'workflow.sysmgr',path: '/workflow/sysmgr'});
components.push({ component: 'workflow/AppMgr',name:'workflow.appmgr',path: '/workflow/appmgr'});
components.push({ component: 'workflow/AppEdit',name:'workflow.appmgr.appedit',path: '/workflow/appmgr/appedit/:id'});
components.push({ component: 'workflow/AppDetail',name:'workflow.myapp.detail',path: '/workflow/myapp/detail/:id'});

components.push({ component: 'workflow/formPage/formDesign',name:'workflow.formdesign',path: '/workflow/formdesign'});
components.push({ component: 'workflow/formPage/flowDesign',name:'workflow.flowdesign',path: '/workflow/flowdesign'});
components.push({ component: 'workflow/formPage/reportDesign',name:'workflow.reportdesign',path: '/workflow/reportdesign'});
components.push({ component: 'workflow/formPage/propertySetting',name:'workflow.propertysetting',path: '/workflow/propertysetting'});
components.push({ component: 'workflow/report',name:'workflow.report',path: '/workflow/report'});

components.push({ component: 'workflow/Frame', name: 'workflow', path: '/workflow'});
components.push({ component: 'workflow/WorkflowTemp', name: 'workflow.form.template', path: '/workflow/formTemplate'});

components.push({ component: 'workflow/initProc', name: 'workflow.initproc', path: '/workflow/initproc'});
components.push({ component: 'workflow/process', name: 'workflow.process', path: '/workflow/process'});

export default components;
