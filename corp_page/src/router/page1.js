let components = [];

//质量管理
components.push({ component: 'quality/device/index', name: '', path: '/device' })//计量器具管理
components.push({ component: 'quality/certificate/Index', name: '', path: '/certificate/index' })//管理记录管理
components.push({ component: 'quality/device/QualityAdd', name: '', path: '/device/add' })
components.push({ component: 'quality/device/AddApplication', name: '', path: '/device/addApp' })
components.push({ component: 'quality/device/Inspection', name: '', path: '/device/Inspection' })
components.push({ component: 'quality/device/newOutside', name: '', path: '/device/newOutside' })//检验记录管理

//管理记录
components.push({ component: 'quality/certificate/management', name: '', path: '/certificate/management' })//合格证管理
components.push({ component: 'quality/certificate/examination', name: '', path: '/certificate/examination' })//合格证审核
components.push({ component: 'quality/certificate/authorize', name: '', path: '/certificate/authorize' })//合格证审核
components.push({ component: 'quality/certificate/print', name: '', path: '/certificate/print' })//合格证打印
components.push({ component: 'quality/certificate/qualification/casting', name: '', path: '/certificate/qualification/cast' })//合格证打印
components.push({ component: 'quality/certificate/construction/new', name: '', path: '/certificate/construction/new' })//合格证打印
components.push({ component: 'quality/inspectionRecord/Index', name: '', path: '/inspectionRecord' })//合格证打印
components.push({ component: 'quality/inspectionRecord/supply/Index', name: '', path: '/inspectionRecord/supply' })//合格证打印
components.push({ component: 'quality/inspectionRecord/supply/Standard', name: '', path: '/inspectionRecord/Standard' })//合格证打印
components.push({ component: 'quality/inspectionRecord/unqualified/Index', name: '', path: '/inspectionRecord/unqualified' })//合格证打印
components.push({ component: 'quality/inspectionRecord/unqualified/correct', name: '', path: '/inspectionRecord/correct' })//合格证打印
components.push({ component: 'quality/inspectionRecord/unqualified/concession', name: '', path: '/inspectionRecord/concession' })//合格证打印
components.push({ component: 'quality/inspectionRecord/unqualified/d_report', name: '', path: '/inspectionRecord/d_report' })//合格证打印
components.push({ component: 'quality/inspectionRecord/unqualified/requisition', name: '', path: '/inspectionRecord/requisition' })//合格证打印

//检验记录管理
//检验记录管理//检验修订记录
components.push({ component: 'quality/quality-record/RevisionRecord', name: '', path: '/quality/quality-record/RevisionRecord' })
components.push({ component: 'quality/quality-record/VisualInspectionEditable', name: '', path: '/quality-record/VisualInspectionEditable' })
components.push({ component: 'quality/quality-record/VisualInspectionEdit', name: '', path: '/quality-record/VisualInspectionEdit' })
components.push({ component: 'quality/quality-record/FerroAlloyEditable', name: '', path: '/quality-record/FerroAlloyEditable' })
components.push({ component: 'quality/quality-record/FerroAlloyEdit', name: '', path: '/quality-record/FerroAlloyEdit' })
components.push({ component: 'quality/quality-record/BrickTubeEditable', name: '', path: '/quality-record/BrickTubeEditable' })
components.push({ component: 'quality/quality-record/BrickTubeEdit', name: '', path: '/quality-record/BrickTubeEdit' })
components.push({ component: 'quality/quality-record/VMethodCoatingEditable', name: '', path: '/quality-record/VMethodCoatingEditable' })
components.push({ component: 'quality/quality-record/VMethodCoatingEdit', name: '', path: '/quality-record/VMethodCoatingEdit' })
components.push({ component: 'quality/quality-record/ResinChromeOreEditable', name: '', path: '/quality-record/ResinChromeOreEditable' })
components.push({ component: 'quality/quality-record/ResinChromeOreEdit', name: '', path: '/quality-record/ResinChromeOreEdit' })
components.push({ component: 'quality/quality-record/CheckLimeEditable', name: '', path: '/quality-record/CheckLimeEditable' })
components.push({ component: 'quality/quality-record/CheckLimeEdit', name: '', path: '/quality-record/CheckLimeEdit' })
components.push({ component: 'quality/quality-record/WaterGlassEditable', name: '', path: '/quality-record/WaterGlassEditable' })
components.push({ component: 'quality/quality-record/WaterGlassEdit', name: '', path: '/quality-record/WaterGlassEdit' })
components.push({ component: 'quality/quality-record/OriginalSandEditable', name: '', path: '/quality-record/OriginalSandEditable' })
components.push({ component: 'quality/quality-record/OriginalSandEdit', name: '', path: '/quality-record/OriginalSandEdit' })
components.push({ component: 'quality/quality-record/ElectrodeEditable', name: '', path: '/quality-record/ElectrodeEditable' })
components.push({ component: 'quality/quality-record/ElectrodeEdit', name: '', path: '/quality-record/ElectrodeEdit' })

//补做通知单
components.push({ component: 'quality/quality-redo/redo', name: '', path: '/quality/quality-redo/redo' })//供应商列表，查询，新建，编辑，状态变更

//新建补做通知单
components.push({ component: 'quality/quality-redo/RedoEdit', name: '', path: '/quality-redo/edit' })//编辑页面
components.push({ component: 'quality/quality-redo/RedoView', name: '', path: '/quality-redo/view' })//详情页面

//检验结果修订记录
//质量查询报表
components.push({ component: 'quality/quality-revision/Index', name: '', path: '/quality/quality-revision/Index' })//质量查询报表 页面 分类
components.push({ component: 'quality/quality-revision/Revision', name: '', path: '/quality/quality-revision/Revision' })//文件批次列表，查询，新建，编辑，状态变更

components.push({ component: 'quality/quality-revision/QualityRevisionView', name: '', path: '/quality-revision/view' })//详情页面



//5.工艺 --->
//基础设置
components.push({ component: 'design/basics/Index', name: '', path: '/basics/Index' })
components.push({ component: 'design/basics/make/Index', name: '', path: '/basics/make/Index' })
components.push({ component: 'design/basics/inspection/Index', name: '', path: '/basics/inspection/Index' })
//基础设置//项目类型管理
components.push({ component: 'design/basics/ProjectTypeManagement/Index', name: '', path: '/basics/ProjectTypeManagement/Index' })
//新材料试用记录
components.push({ component: 'design/new-material/Index', name: '', path: '/new-material/Index' })
components.push({ component: 'design/new-material/AddNewMaterial', name: '', path: '/new-material/AddNewMaterial' })
components.push({ component: 'design/new-material/EditNewMaterial', name: '', path: '/new-material/EditNewMaterial' })
//合同铸件号维护
components.push({ component: 'design/contract-castings/Index', name: '', path: '/contract-castings/Index' })
//合同工艺制作
components.push({ component: 'design/contract-design/Index', name: '', path: '/contract-design/Index' })
//合同工艺制作//合同项目分类
components.push({ component: 'design/contract-design/classify/Index', name: '', path: '/contract-design/classify/Index' })
//合同工艺制作//合同签订后工艺制作
components.push({ component: 'design/contract-design/make/Index', name: '', path: '/contract-design/make/Index' })

















export default components;

