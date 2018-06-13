
$(function() {
	//编辑之前的节点
	var onBeforeEditNode;
	//创建树
	$("#tt").tree({
		url : 'TreeServlet',
		lines : true,
		checkbox : true,
		//添加右键菜单，添加文件、文件夹和删除
		onContextMenu : function(e, node) {
			e.preventDefault();
			$(this).tree('select', node.target);
			$('#mm').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
	    //双击编辑节点
		onDblClick: function(node){
			$(this).tree('beginEdit',node.target);
		},
		
		//编辑节点事件之前获取原节点
		onBeforeEdit:function(node){
			onBeforeEditNode=node;
			console.log(node);
		},
		//编辑节点之后进行操作
		onAfterEdit: function(node){
			//如果节点没有修改则不需要操作
			if(onBeforeEditNode.text==node.text){
				return false;
			}
			//如果修改了，则调用ajax把本的文件名也改了
			$.ajax({
		        type: "post",
		        dataType: "json",
		        url: 'RenameFileServlet',
		        data: {"node":JSON.stringify(node)},
		        success: function (data) {
		        	if(data!=null&&data!=""){
		        			alert("修改文件名成功");
		        			//暂时未完善，直接重新加载树。。。。。。
		        			$('#tt').tree('reload');
		        		
		        	}else{
	        			alert("修改失败，文件夹名已经存在");
	        			$('#tt').tree('reload');
	        		}
		        }
		    });
		}
		
	});

})

//添加文件
function appendFile() {
	var t = $('#tt');
	var node = t.tree('getSelected');
	if(node.state==undefined){
		alert("只有文件夹才能添加文件");
		return false;
	}else{
		$.ajax({
        type: "post",
        dataType: "json",
        url: 'AppendNodeServlet',
        data: {"node":JSON.stringify(node)},
        success: function (data) {
        	if(data!=null){
        		t.tree('append', {
        		parent : (node ? node.target : null),
        		data : [ {
        			id:data.id,
        			text:data.text,
        			attributes:data.attributes
        		}]
        	});
        	}
        }
    });
	}
}

//添加文件夹
function appendFolder() {
	var t = $('#tt');
	var node = t.tree('getSelected');
	if(node.state==undefined){
		alert("只有文件夹才能添加文件夹");
		return false;
	}else{
		$.ajax({
        type: "post",
        dataType: "json",
        url: 'AppendFolderServlet',
        data: {"node":JSON.stringify(node)},
        success: function (data) {
        	if(data!=null){
        		t.tree('append', {
        		parent : (node ? node.target : null),
        		data : [ {
        			id:data.id,
        			text:data.text,
        			attributes:data.attributes,
        			state:'closed'
        		}]
        	});
        	}
        }
    });
	}
}

//删除文件
function removeit() {
	var node = $('#tt').tree('getSelected');
	 $.ajax({
	        type: "post",
	        dataType: "json",
	        url: 'DelFolderServlet',
	        data: {"files":JSON.stringify(node)},
	        success: function (data) {
	        	if(data==true){
	        		alert("删除成功");
	        		$('#tt').tree('remove', node.target);
	        	}else{
	        		alert("删除失败");
	        	}
	        }
	    });
}

function home(){
	$.ajax({
        type: "post",
        url: 'HomeAddressServlet',
        success: function (data) {
        	console.log(data);
        }
    });
}

//点击事件获取节点并且删除文件夹
/*function getChecked(){
//获取选中的文件夹
var checkedArray= $("#tt").tree('getChecked');
//是否删除
if(confirm("是否删除文件夹")){
	 $.ajax({
	        type: "post",
	        dataType: "json",
	        url: 'DelFolderServlet',
	        data: {"files":JSON.stringify(checkedArray)},
	        success: function (data) {
	        	("#tt").tree("reload");
	        }
	    });

}
return false;
}*/