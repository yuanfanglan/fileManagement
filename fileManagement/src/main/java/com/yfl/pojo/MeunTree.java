package com.yfl.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
id：节点ID，对加载远程数据很重要。
text：显示节点文本。
state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
checked：表示该节点是否被选中。
pid：父节点
attributes: 被添加到节点的自定义属性。
*/
public class MeunTree {

	private String id;
	private String pid;
	private String text;
	private String state;
	private boolean checked;
	private Map<String, Object> attributes = new HashMap<String, Object>(); // 添加到节点的自定义属性
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	@Override
	public String toString() {
		return "MeunTree [id=" + id + ", pid=" + pid + ", text=" + text + ", state=" + state + ", checked=" + checked
				+ ", attributes=" + attributes + "]";
	}

	
}
