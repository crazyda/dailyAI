//============================表格操作方法开始===========================

//显示或者隐鲹选择区域和表格区域；

//显示或者隐藏对应的select选项；
function showOrHideSelectArea(obj) {
    //var size=$(".checkArea input:checked").size();
    var size = obj.parents(".standardArea").find(".checkArea input:checked").size();

    var thisArea = obj.parents(".standardArea");
    if (size) {
        thisArea.find(".selectArea").css("display", "inline");
        thisArea.find(".tableArea").css("display", "block");
    } else {
        thisArea.find(".selectArea").css("display", "none");
        thisArea.find(".tableArea").css("display", "none");
    }
    
}
function showOrHideSelect(selectObj) {
    var checkValue = $(selectObj).val();
    $.each(selectObj.parents(".standardArea").find(".selectArea select"), function (index, obj) {
        var selectValue = $(obj).data("value");
        if (selectValue == checkValue) {
            $(obj).toggle();
        }
    });
}

//增加标题头（就是增加了一级商品规格，表格就应该增加相应的列；）
function addTableHead(selectObj) {
    var headName = selectObj.val();
    selectObj.parents(".standardArea").find(".tableArea .headLine").prepend('<th>' + headName + '</th>')
}

//删除标题头；
function delTableHead(selectObj) {
    var headName = selectObj.val();
    var ths = selectObj.parents(".standardArea").find(".tableArea .headLine th");
    $.each(ths, function (index, obj) {
        var name = $(obj).text();
        if (name == headName) {
            $(obj).remove();
            return index;
        }
    });
}
var o=null;
//自动根据是选中还是没选中，来删除或者增加表头；
function autoTableHead(selectObj) {
	console.log(o);
	if(o!=null){
		showOrHideSelect(o);
		delTableHead(o);
		 $(o).prop("checked", false);
	}
	
    var isChecked = selectObj.prop("checked");
    if (isChecked) {
        addTableHead(selectObj);
    } else {
        delTableHead(selectObj);
    }
    o=selectObj;
}



//点击一级商品规格的点击事件；
function showSellectAndTableHead(obj) {

    //检查checkBox的数量是否超过上限（三个）；
    var numberCheckResult = checkCheckBoxNumber($(obj));
    if (!numberCheckResult) {
        alert("只能选择3种规格");
        $(obj).prop("checked", false);
        return;
    }

    //如果是在tableArea中有内容的情况下点击了checkBox，那么就清空表格内容；
    clearTableArea($(obj));

    //显示或者隐藏选择区域；
    showOrHideSelectArea($(obj));
    
    //这个一级商品规格也是要提交的，需要自动增加或删除需要提交的选项；
    autoFirstStandard($(obj));

    //显示或者隐藏对应的select选项；
    showOrHideSelect($(obj));

    //自动增加或减少表格头；
    autoTableHead($(obj));
    

    
}
//增加具体的商品规格；  
function addItem(obj, mallType, inputNum) {

    var checkResult = checkRepeat($(obj), inputNum);
    if (checkResult) {
        alert("此规格已经存在；");
        return;
    }

    var addTest = $('<tr></tr>');
    $.each($(obj).parents(".standardArea").find(".selectArea select:visible"), function (index, obj1) {
        var selectValue = $(obj1).val();
        $.each($(obj1).find("option"), function (index2, obj2) {
            if (selectValue == $(obj2).val()) {
                addTest.prepend('"<td>' + $(obj2).text() + '<input name="detailsValue' + (index + 1) + '[]" type="hidden" value="' + $(obj2).val() + '"/><input name="detailsName' + (index + 1) + '[]" type="hidden" value="' + $(obj2).text() + '"/></td>"');
            }
        });
    });
    if (mallType == 1) {
        addTest.append('<td><input name="repertory[]" /></td>');
        addTest.append('<td><input name="price[]" /></td>');
        /*addTest.append('<td><input name="redPaper[]" /></td>');*/
    } else if (mallType == 2) {
        addTest.append('<td><input name="repertory[]" /></td>');
        addTest.append('<td><input name="score[]" /></td>');
    } else if (mallType == 3) {
        addTest.append('<td><input name="repertory[]" /></td>');
        addTest.append('<td><input name="price[]" /></td>');
    } else if (mallType == 4) {
        addTest.append('<td><input name="repertory[]" /></td>');
        addTest.append('<td><input name="price[]" /></td>');
       /* addTest.append('<td><input name="redPaper[]" /></td>');*/
    } else if (mallType == 5) {
        addTest.append('<td><input name="repertory[]" /></td>');
        addTest.append('<td><input name="price[]" /></td>');
        /*addTest.append('<td><input name="redPaper[]" /></td>');*/
    } else if (mallType == 6) {
        addTest.append('<td><input name="repertory[]" /></td>');
    }
    addTest.append('<td><input type="button" onclick="delTr(this);" value="删除"/></td>');

    $(obj).parents(".standardArea").find(".tableArea table").append(addTest);
}

//删除一行的操作；
function delTr(obj) {
    $(obj).parent().parent().remove();
}

//检查是否重复添加的规格是否有重复；
function checkRepeat(obj, inputNum) {
    var selects = obj.parents(".standardArea").find(".selectArea select:visible");
    var selectSize = selects.size();

    var v1;
    var v2;
    var v3;
    $.each(selects, function (index, obj) {
        if (index == 0) {
            v1 = $(obj).val();
        } else if (index == 1) {
            v2 = $(obj).val();
        } else if (index == 2) {
            v3 = $(obj).val();
        }
    });

    //遍历所有的增加的行；
    var allInputs = obj.parents(".standardArea").find(".tableArea table tr td input");
    if (allInputs.size() == 0) {
        return false;
    }
    var circleTimes = allInputs.size() / (inputNum + selectSize * 2);
    for (var i = 0; i < circleTimes; i++) {
        if (selectSize == 1) {
            if (v1 == $(allInputs.get((2 + inputNum) * i)).val()) {
                return true;
            }
        }
        if (selectSize == 2) {
            var i1 = $(allInputs.get((4 + inputNum) * i)).val();
            var i2 = $(allInputs.get((4 + inputNum) * i + 2)).val();
            var a1 = [i1, i2].sort();
            var a2 = [v1, v2].sort();
            if (a1[0] == a2[0] && a1[1] == a2[1]) {
                return true;
            }
        }
        if (selectSize == 3) {
            var i1 = $(allInputs.get((6 + inputNum) * i)).val();
            var i2 = $(allInputs.get((6 + inputNum) * i + 2)).val();
            var i3 = $(allInputs.get((6 + inputNum) * i + 4)).val();

            var a1 = [i1, i2, i3].sort();
            var a2 = [v1, v2, v3].sort();
            if (a1[0] == a2[0] && a1[1] == a2[1] && a1[2] == a2[2]) {
                return true;
            }
        }
    }
    return false;
}

//当表格中已经有规格内容时，如果点击checkArea中的选项时，tableArea中的内容应该清空；
function clearTableArea(obj) {
    var trs = obj.parents(".standardArea").find(".tableArea table tr");
    if (trs.size() > 1) {
        obj.parents(".standardArea").find(".tableArea table tr:gt(0)").remove();
    }
}

//检查选中的checkBox的数量是否超过了三个；
function checkCheckBoxNumber(obj) {
    var number = obj.parent(".checkArea").find("input:checked").size();
    if (number > 3) {
        return false;
    } else {
        return true;
    }
}

//自动增加或者删除一级商品规格的input；
function autoFirstStandard(obj) {
    var value = obj.val();
    var key = obj.data("key");
    var isChecked = obj.prop("checked");

    if (isChecked) {
        obj.parents(".standardArea").find(".checkArea").append('<input type="hidden" name="firstStandardKey[]" value="' + key + '" />');
        obj.parents(".standardArea").find(".checkArea").append('<input type="hidden" name="firstStandardName[]" value="' + value + '" />');
    } else {
        var allInputs = obj.parents(".standardArea").find(".checkArea input:hidden");
        $.each(allInputs, function (index, obj) {
            var inputName = $(obj).prop("name");
            var v = $(obj).val();
            if (inputName == "firstStandardKey[]" && v == key) {
                $(obj).remove();
            }
            if (inputName == "firstStandardName[]" && v == value) {
                $(obj).remove();
            }
        });
    }

}

//============================表格操作方法结束===========================


//============================表格数据回显方法开始===========================

//回显数据；
function returnBack(obj) {
    var index = obj.parent().index();
    var li = $('.tab .tab_con li').eq(index);
    var tableTrNum = li.find(".tableArea table tbody tr").size();

    if (tableTrNum) {
        //如果表格中有数据，那么应该显示的有规格的商品表格，不是默认的无商品规格；
        li.find(".sellerMallNoStandardCheckBox").click();

        //将表格中selectArea和tableArea中的内容都显示出来；
        li.find(".selectArea").css({'display': 'block'});
        li.find(".tableArea").css({'display': 'block'});

        //勾选checkArea区域中的对应一级商品规格；
        checkCheckAreaCheckBox(li.find(".checkArea"));

        //将selectArea中与checkArea中勾选的对应选项显示出来；
        checkSelectAreaCheckBox(li.find(".selectArea"));
    }
}

//回显时，将checkArea中的对应选项勾选；
function checkCheckAreaCheckBox(checkArea) {
    var checkBoxs = checkArea.find("[type='checkbox']");
    var checkedKey = checkArea.find("[name='firstStandardKey[]']");
    $.each(checkBoxs, function (index, obj) {
        var key = $(obj).data("key");
        $.each(checkedKey, function (index2, obj2) {
            var v = $(obj2).val();
            if (v == key) {
                $(obj).prop("checked", true);
            }
        });
    });
}

//回显时，将selectArea中的对应勾选的内容显示出来；
function checkSelectAreaCheckBox(selectArea) {
    var selects = selectArea.find("select");
    var checkedKey = selectArea.prev(".checkArea").find("[name='firstStandardKey[]']");
    $.each(selects, function (index, obj) {
        var key = $(obj).data("key");
        $.each(checkedKey, function (index2, obj2) {
            var v = $(obj2).val();
            if (v == key) {
                $(obj).css({"display": "inline"});
            }
        });
    });
}

//============================表格数据回显方法结束===========================

//----------地区选择--------------//

function changeZone(){
	  var ad = event.srcElement;
	  var province = document.getElementById("provinceId");
	  var city = document.getElementById("cityId");
	  var county = document.getElementById("countyId");
			if(parseInt(ad.value)>0){
				if(ad==province){
					city.options.length=0;				
				}
				county.options.length=0;
				
				$.ajax({
					type:"post",
					url:$("input.mypath").val()+"professional/user/changeZone",
					data: "zonesid="+ad.value,        
			        dataType: "text",
			        success: function (data){
			        	var json=eval( '('+data+ ')' );
		    	    	 if(ad==province){
		    	    		 city.options.add(new Option("请选择", ""));
		    	    	 }
		    	    	 county.options.add(new Option("请选择", ""));
		    	    	 for(var i in json){	
		    	    		 if(ad==province){
		    	    			 city.options.add(new Option(json[i],i));
		    	    		 }else{
		    	    		 	county.options.add(new Option(json[i],i));
		    	    		 }
		    	    	 }
			
			        }
				})
			}
			
	}
