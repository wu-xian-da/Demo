//确认删除
function confirmDel(tit){
	if(tit == null){
		tit = '删除';
	}
	if(confirm("您确定要"+tit+"吗?")){
		return true;
	}
	else{
		return false;
	}
}