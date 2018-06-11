//创建树
$(function() {
	$("#tt").tree({
		url : 'TreeServlet',
		lines : true,
		checkbox : true,
		//添加右键菜单，添加和删除
		onContextMenu : function(e, node) {
			e.preventDefault();
			$(this).tree('select', node.target);
			$('#mm').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
	    //编辑节点
		onDblClick: function(node){
			$(this).tree('beginEdit',node.target);
		}

	});

})

//添加文件
function append() {
	var t = $('#tt');
	var node = t.tree('getSelected');
	t.tree('append', {
		parent : (node ? node.target : null),
		data : [ {
			text : 'new item1',
			state:'closed'
		}]
	});
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