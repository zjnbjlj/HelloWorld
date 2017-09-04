package wzcc.com.helloworld;

import com.alibaba.fastjson.JSON;
import com.wzcc.toollibary_jlj.StringHelper;
import wzcc.com.helloworld.model.ModelResult;


public class CommonString {
	
		// 网络名称
		public static String APNname = "wzghj";
		// 下载文件夹名
		public static String FoldName = "wzghj";
		public static String FoldImgName = "/"+FoldName+"/images";
		public static String AppPackageName = "com.wzcc.wzghj";
		// 页面地址
		public static String DownLoadUrl = "http://app.wzcc.com/app/156";
		// 应用升级地址	
		public static String VersionUpURL = "http://app.wzcc.com/WebCall.App.DataApp.axd/LookUp?id=156&os=android";
		public static final int GOTOLOGIN = 1010;
		public String ServiceUrl = MyApp.getInstance().getServerUrl();
		
		//登录接口
		public String LoginUrl = ServiceUrl + "/zfapp/login";
		//搜索港口企业
		public String SearchGkListUrl = ServiceUrl + "/zfapp/gkqyList";
		//港口设施设备
		public String GkEquipUrl = ServiceUrl +"/zfapp/jxsbxxBasic";
		//港口泊位查询
		public String GkParkUrl = ServiceUrl+"/zfapp/bwxxSelect";
		//港口从业人员查询
		public String GKStaffUrl = ServiceUrl + "/zfapp/gkcyryBasic";
		//港口企业安全隐患查询
		public String GKDangersUrl = ServiceUrl + "/zfapp/yhjzgSelect";
		//港口企业年审情况
		public String GKNSUrl = ServiceUrl + "/zfapp/gkqynsdjxx";
		//港口企业许可查询
		public String GKPermissionUrl = ServiceUrl + "/zfapp/gkqyList";
		//港口企业生产作业
		public String GKSCZYUrl = ServiceUrl + "/zfapp/sczySelect";
		
		//搜索水运企业
		public String SearchSyListUrl = ServiceUrl + "/zfapp/syqyList";
		//水运企业船舶列表
		public String SYShipListUrl = ServiceUrl + "/zfapp/sycbList";
		//水运企业海机务人员年审明细
		public String SYStaffUrl = ServiceUrl + "/zfapp/syhjglryBasic";
		//水运企业年审情况
		public String SYNSUrl = ServiceUrl + "/zfapp/syqyndscSelect";
		//水运企业安全隐患
		public String SYDangerUrl = ServiceUrl +"/zfapp/yhjzgSelect";
		//水运企业许可
		public String SYPermissionUrl = ServiceUrl + "/zfapp/syqyList";
		//水运企业备案
		public String SYBAUrl = ServiceUrl +"/zfapp/hjwryxgbaSelect";
		
		//水运企业年审情况
		public String SYNSSearchUrl = ServiceUrl + "/zfapp/syqyndscList";
		//企业生产经营情况统计
		public String SYNSProduceUrl = ServiceUrl + "/zfapp/qyscjyqktjSelect";
		//运输证核查明细
		public String SYNSYSZUrl = ServiceUrl + "/zfapp/yszhcmxSelect";
		//代管船舶核查明细
		public String SYNSShipUrl = ServiceUrl + "/zfapp/dgcbhcmxSelect";
		//海机务人员年审明细
		public String SYNSStaffUrl = ServiceUrl + "/zfapp/syhjglryBasic";
		
		//船舶搜索
		public String ShipSearchUrl = ServiceUrl + "/zfapp/cbList";
		//船舶年审情况
		public String ShipNSUrl = ServiceUrl + "/zfapp/sycbns";
		//船舶处罚情况
		public String ShipPunishUrl = ServiceUrl + "/zfapp/cbcfxx";
		//船检信息
		public String ShipCheckInfoUrl = ServiceUrl + "/zfapp/cjzs";
		//运单信息
		public String ShipTransInfoUrl = ServiceUrl + "/zfapp/sydzyd";
		//靠泊码头
		public String ShipParkInfoUrl = ServiceUrl + "/zfapp/cbjcgxx";
		
		//搜索航道
		public String SearchHdListUrl = ServiceUrl + "/zfapp/hdjcxxList";
		//获取航段列表
		public String SearchHduanListUrl = ServiceUrl + "/zfapp/hddlxx";
		//获取航标列表
		public String SearchHBListUrl = ServiceUrl + "/zfapp/hbxx";
		//获取涉航建筑物列表
		public String SearchHDBuildingListUrl = ServiceUrl + "/zfapp/hdjzwxxSelect";
		
		//搜索岸线
		public String SearchAxListUrl = ServiceUrl + "/zfapp/axlyghList";
		//搜索岸线使用情况
		public String SearchAxUseInfoListUrl = ServiceUrl + "/zfapp/axsyqk";
		//岸线泊位查询
		public String AXParkUrl = ServiceUrl+"/zfapp/axbwxx";
		
		
		// 船舶监测
		public String ShipWatchSearchUrl = ServiceUrl + "/zfapp/cbDataSource";
		//获取船舶轨迹
		public String ShipTraceUrl = ServiceUrl + "/zfapp/selectCbData";
		//搜索周边信息
		public String GetSurroundingBuildingUrl = ServiceUrl + "/zfapp/getSurroundingBuling";
		
		//任务对象-获取企业列表
		public String GetTaskObject_QY = ServiceUrl + "/zfapp/selectQy";
		//任务对象-获取航道列表
		public String GetTaskObject_HD = ServiceUrl + "/zfapp/selectHd";
		//任务对象-获取在建工程列表
		public String GetTaskObject_XM = ServiceUrl + "/zfapp/selectGc";
		//执法人员获取
		public String GetPeopleUrl = ServiceUrl + "/zfapp/zfrySelect";
		//添加执法任务
		public String InsertTaskUrl = ServiceUrl + "/zfapp/insertZfrw";
		//搜索执法任务
		public String SearchNormalTaskUrl = ServiceUrl + "/zfapp/zfSelect";
		//搜索隐患任务
		public String SearchDangerTaskUrl = ServiceUrl + "/zfapp/rwYhjzgSelect";	
		//获取具体隐患任务
		public String GetDangerTaskUrl = ServiceUrl + "/zfapp/yhjzgSelectById";
		//提交验收结果
		public String PostDangerTaskUrl = ServiceUrl + "/zfapp/yhjzgUpdate";
		//回退隐患任务
		public String ReturnDangerTaskUrl = ServiceUrl + "/zfapp/backTo";
		//转发隐患任务
		public String SendDangerTaskUrl = ServiceUrl + "/zfapp/turnTo";
		//完成隐患任务
		public String FinishDangerTaskUrl = ServiceUrl + "/zfapp/endRw";
		//获取隐患任务流程
		public String GetDangerTaskProcessUrl = ServiceUrl + "/zfapp/rwlcxq";
		//获取任务步骤列表
		public String GetDangerBZUrl = ServiceUrl+ "/zfapp/bzList";
		//获取任务步骤执行人员列表
		public String GetDangerBZPeopleUrl = ServiceUrl+ "/zfapp/ryList";
		//搜索预警检查
		public String SearchWarnTaskUrl = ServiceUrl + "/zfapp/yjSelect";
		//获取具体预警检查任务
		public String GetWarnTaskUrl = ServiceUrl + "/zfapp/yjSelectById";
		//核查预警检查任务
		public String PostWarnTaskUrl = ServiceUrl + "/zfapp/qyyjjcdjUpdate";
		//下载附件
		public String GetFileUrl = ServiceUrl+ "/zfapp/download?fileName=%s&fileId=%s";

		
		
		//查询监督执法记录
		public String SearchSuperviseRecordUrl = ServiceUrl + "/zfapp/selectZfjlList";
		//任务名称获取
		public String GetTaskUrl = ServiceUrl + "/zfapp/zfmcSelect";
		//某任务下的执法人员获取
		public String GetPeopleByTaskUrl = ServiceUrl + "/zfapp/rwwcrSelect";
		//获取违法情况总表
		public String GetIllegalUrl = ServiceUrl + "/zfapp/wfqkSelect";
		//新增执法记录
		public String AddSuperviseRecordUrl = ServiceUrl + "/zfapp/insertZfjl";
		//获取执法记录
		public String GetSuperviseRecordUrl = ServiceUrl + "/zfapp/updateZfjl";
		//保存修改后执法记录
		public String UpdateSuperviseRecordUrl = ServiceUrl + "/zfapp/updateZfjlSave";
		//删除执法记录
		public String DeleteSuperviseRecordUrl = ServiceUrl + "/zfapp/deleteZfjl";
		//读取图片
		public String GetPicUrl = ServiceUrl+ "/zfapp/getTpic";
		
		//查询日常执法记录
		public String SearchNormalRecordUrl = ServiceUrl + "/zfapp/selectRcjcList";
		//删除日常执法记录
		public String DeleteNormalRecordUrl = ServiceUrl + "/zfapp/deleteRcjc";
		//检查对象-港口企业选择
		public String getGKlistUrl = ServiceUrl + "/zfapp/getGkqy";
		//检查对象-危货企业选择
		public String getWHlistUrl = ServiceUrl + "/zfapp/getWhqy";
		//检查对象-水运企业选择
		public String getSYlistUrl = ServiceUrl + "/zfapp/getSyqy";
		//检查对象-水运船舶选择
		public String getSYShiplistUrl = ServiceUrl + "/zfapp/getSycb";
		//检查对象-渡埠选择
		public String getDBlistUrl = ServiceUrl + "/zfapp/getDb";
		//检查对象-港口企业选择
		public String getDClistUrl = ServiceUrl + "/zfapp/getDc";
		//获取执法记录
		public String GetNormalRecordUrl = ServiceUrl + "/zfapp/selectRcjcById";
		//新增日常检查单
		public String AddNormalRecordUrl = ServiceUrl +"/zfapp/insertRcjc";
		//修改日常检查单
		public String UpdateNormalRecordUrl = ServiceUrl + "/zfapp/updateRcjcSave";
		
		
		//所属机构列表
		public String OrganizesListUrl = ServiceUrl + "/zfapp/sjssjgList";
		//船舶类型列表
		public String ShipTypeListUrl = ServiceUrl + "/zfapp/getCblx";
		
		public static ModelResult JsonParse(String result){
			ModelResult modelResult;
			try {
				modelResult = JSON.parseObject(result, ModelResult.class);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return modelResult;
		}
		
		public static boolean isNeedRelogin(String result){
			ModelResult modelResult = JsonParse(result);
			if (modelResult != null && !modelResult.success && !StringHelper.isEmpty(modelResult.msg) && modelResult.msg.equals("0")) {
				return true;
			}
			return false;
		}
				
}
