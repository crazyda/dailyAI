function checked(_flag,index){
	//积分兑换验证
	if(_flag==1){
    	var noStandardRepertory = $("form:eq(" + index + ") input[name='noStandardRepertory']").val();
        var noStandardScore = $("form:eq(" + index + ") input[name='noStandardScore']").val();
        var displayPrice = $("form:eq(" + index + ") input[name='displayPrice']").val();
        var countLimit = $("form:eq(" + index +") input[name='countLimit']").val();
    	var exchangeArea = $("form:eq(" + index +") input[name='exchangeArea']:checked").val();
    	var transportationType = $("form:eq(" + index + ") input[name='transportationType']:checked").val();
    	var transportationPrice = $("form:eq(" + index + ") input[name='transportationPrice']").val();
    	var addedTime = $("form:eq(" + index + ") input[name='addedTime']").val();
    	var shelvesTime = $("form:eq(" + index + ") input[name='shelvesTime']").val();
    	
		var _repertory =$("form:eq(" + index + ") input[name='repertory[]']").val();
		var _checkguige =$("form:eq(" + index + ") input.sellerMallNoStandardCheckBox:checked").val();
		if(!_repertory&&!_checkguige){
			layer.alert("积分兑换的库存不能为空");
	      	_canpost = false;
	      	return false;
		}
    	if(!noStandardRepertory&&!_repertory){
		      	layer.alert("积分兑换的库存不能为空");
		      	_canpost = false;
		      	return false;
    	}
        if(!noStandardScore){
        	layer.alert("积分兑换的积分不能为空");
        	_canpost = false;
        	return false;
        }
        if(!displayPrice){
	      	layer.alert("积分兑换的市场价不能为空");
	      	_canpost = false;
	      	return false;
	     }
	    if (!countLimit) {
			layer.alert("积分兑换的每人限购不能为空");
			_canpost=false;
			return false;
		}
	    if(!exchangeArea){
	      	layer.alert("积分兑换的兑换区域不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    if(!transportationType){
	      	layer.alert("积分兑换的配送方式不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    if(transportationType==2){
			  if(!transportationPrice){
			      	layer.alert("积分兑换的邮费不能为空");
			      	_canpost = false;
			      	return false;
			  }
		 }	
	    if(!addedTime){
	      	layer.alert("积分兑换的上架时间不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    if(!shelvesTime){
	      	layer.alert("积分兑换的下架时间不能为空");
	      	_canpost = false;
	      	return false;
	    }	 
			
			
	}
	
	//商家换货区验证
	if(_flag==2){
		var _repertory =$("form:eq(" + index + ") input[name='repertory[]']").val();
		var _checkguige =$("form:eq(" + index + ") input.sellerMallNoStandardCheckBox:checked").val();
		if(!_repertory&&!_checkguige){
			layer.alert("商家换货区库存不能为空");
	      	_canpost = false;
	      	return false;
		}
		var noStandardRepertory=$("form:eq(" + index + ") input[name='noStandardRepertory']").val();
		if(!noStandardRepertory&&!_repertory){
	      	layer.alert("商家换货区库存不能为空");
	      	_canpost = false;
	      	return false;
	    }
		var displayPrice=$("form:eq(" + index + ") input[name='displayPrice']").val();
		if (!displayPrice) {
			layer.alert("商家换货区市场价不能为空");
			_canpost=false;
			return false;
		}
		var transportationType = $("form:eq(" + index + ") input[name='transportationType']:checked").val();
    	if(!transportationType){
	      	layer.alert("商家换货区配送方式不能为空");
	      	_canpost = false;
	      	return false;
	    }
    	var want= $("form:eq(" + index + ") input[name='want']").val();
    	if (!want) {
			layer.alert("商家换货区想换什么不能为空");
			_canpost=false;
			return false;
		}
    	var startQuantity =$("form:eq(" + index + ") input[name='startQuantity']").val();
    	if (!startQuantity) {
			layer.alert("商家换货区起换数量不能为空");
			_canpost=false;
			return false;
		}
	}
	
	//总部商城验证
	if(_flag==3){
		var _price=$("form:eq(" + index + ") input[name='price[]']").val();
		var _repertory =$("form:eq(" + index + ") input[name='repertory[]']").val();
		var _checkguige =$("form:eq(" + index + ") input.sellerMallNoStandardCheckBox:checked").val();
		if(!_repertory&&!_checkguige){
			layer.alert("总部商城库存不能为空");
	      	_canpost = false;
	      	return false;
		}
		if(!_price&&!_checkguige){
			layer.alert("总部商城单价不能为空");
	      	_canpost = false;
	      	return false;
		}
		var noStandardRepertory=$("form:eq(" + index + ") input[name='noStandardRepertory']").val();
		if(!noStandardRepertory&&!_repertory){
	      	layer.alert("总部商城库存不能为空");
	      	_canpost = false;
	      	return false;
	    }
		var noStandardPrice =$("form:eq(" + index + ") input[name='noStandardPrice']").val();
		if (!noStandardPrice&&!_price) {
			layer.alert("总部商城单价不能为空");
			_canpost=false;
			return false;
		}
		var displayPrice=$("form:eq(" + index + ") input[name='displayPrice']").val();
		if (!displayPrice) {
			layer.alert("总部商城市场价不能为空");
			_canpost=false;
			return false;
		}
		var transportationType = $("form:eq(" + index + ") input[name='transportationType']:checked").val();
    	var transportationPrice = $("form:eq(" + index + ") input[name='transportationPrice']").val();
    	 if(!transportationType){
 	      	layer.alert("总部商城配送方式不能为空");
 	      	_canpost = false;
 	      	return false;
 	    }
 	    if(transportationType==2){
 			  if(!transportationPrice){
 			      	layer.alert("总部商城邮费不能为空");
 			      	_canpost = false;
 			      	return false;
 			  }
 		 }
 	   var addedTime = $("form:eq(" + index + ") input[name='addedTime']").val();
 	   var shelvesTime = $("form:eq(" + index + ") input[name='shelvesTime']").val();
 	   if(!addedTime){
	      	layer.alert("总部商城上架时间不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    if(!shelvesTime){
	      	layer.alert("总部商城下架时间不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    /*var placeOfProduction = $("form:eq(" + index + ") input[name='placeOfProduction']").val();
	    if(!placeOfProduction){
	      	layer.alert("总部商城所在产地不能为空");
	      	_canpost = false;
	      	return false;
	    }*/
	    var pack =$("form:eq(" + index + ") input[name='pack']").val();
	    if(!pack){
	      	layer.alert("总部商城商品包装不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    
	    
	    
	}
	
	//会员免单区验证
	if (_flag==4) {
		var _repertory =$("form:eq(" + index + ") input[name='repertory[]']").val();
		var _checkguige =$("form:eq(" + index + ") input.sellerMallNoStandardCheckBox:checked").val();
		if(!_repertory&&!_checkguige){
			layer.alert("会员免单区库存不能为空");
	      	_canpost = false;
	      	return false;
		}
		var noStandardRepertory=$("form:eq(" + index + ") input[name='noStandardRepertory']").val();
		if(!noStandardRepertory&&!_repertory){
	      	layer.alert("会员免单区库存不能为空");
	      	_canpost = false;
	      	return false;
	    }
		var displayPrice=$("form:eq(" + index + ") input[name='displayPrice']").val();
		if (!displayPrice) {
			layer.alert("会员免单区市场价不能为空");
			_canpost=false;
			return false;
		}
		var transportationType = $("form:eq(" + index + ") input[name='transportationType']:checked").val();
    	var transportationPrice = $("form:eq(" + index + ") input[name='transportationPrice']").val();
    	 if(!transportationType){
 	      	layer.alert("会员免单区配送方式不能为空");
 	      	_canpost = false;
 	      	return false;
 	    }
 	    if(transportationType==2){
 			  if(!transportationPrice){
 			      	layer.alert("会员免单区邮费不能为空");
 			      	_canpost = false;
 			      	return false;
 			  }
 		 }
 	   var addedTime = $("form:eq(" + index + ") input[name='addedTime']").val();
 	   var shelvesTime = $("form:eq(" + index + ") input[name='shelvesTime']").val();
 	   if(!addedTime){
	      	layer.alert("会员免单区上架时间不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    if(!shelvesTime){
	      	layer.alert("会员免单区下架时间不能为空");
	      	_canpost = false;
	      	return false;
	    }
	}
	
	//久久特惠验证
	if (_flag==5) {
		var _price=$("form:eq(" + index + ") input[name='price[]']").val();
		var _repertory =$("form:eq(" + index + ") input[name='repertory[]']").val();
		var _checkguige =$("form:eq(" + index + ") input.sellerMallNoStandardCheckBox:checked").val();
		if(!_price&&!_checkguige){
			layer.alert("久久特惠单价不能为空");
			_canpost=false;
			return false;
		}
		if(!_repertory&&!_checkguige){
			layer.alert("久久特惠库存不能为空");
	      	_canpost = false;
	      	return false;
		}
		var noStandardRepertory=$("form:eq(" + index + ") input[name='noStandardRepertory']").val();
		if(!noStandardRepertory&&!_repertory){
	      	layer.alert("久久特惠库存不能为空");
	      	_canpost = false;
	      	return false;
	    }
		var noStandardPrice =$("form:eq(" + index + ") input[name='noStandardPrice']").val();
		if (!noStandardPrice&&!_price) {
			layer.alert("久久特惠单价不能为空");
			_canpost=false;
			return false;
		}
		var displayPrice=$("form:eq(" + index + ") input[name='displayPrice']").val();
		if (!displayPrice) {
			layer.alert("久久特惠市场价不能为空");
			_canpost=false;
			return false;
		}
		var transportationType = $("form:eq(" + index + ") input[name='transportationType']:checked").val();
    	var transportationPrice = $("form:eq(" + index + ") input[name='transportationPrice']").val();
    	 if(!transportationType){
 	      	layer.alert("久久特惠配送方式不能为空");
 	      	_canpost = false;
 	      	return false;
 	    }
 	    if(transportationType==2){
 			  if(!transportationPrice){
 			      	layer.alert("久久特惠邮费不能为空");
 			      	_canpost = false;
 			      	return false;
 			  }
 		 }
 	   var addedTime = $("form:eq(" + index + ") input[name='addedTime']").val();
 	   var shelvesTime = $("form:eq(" + index + ") input[name='shelvesTime']").val();
 	   if(!addedTime){
	      	layer.alert("久久特惠上架时间不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    if(!shelvesTime){
	      	layer.alert("久久特惠下架时间不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    var placeOfProduction = $("form:eq(" + index + ") input[name='placeOfProduction']").val();
	    if(!placeOfProduction){
	      	layer.alert("久久特惠所在产地不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    var pack =$("form:eq(" + index + ") input[name='pack']").val();
	    if(!pack){
	      	layer.alert("久久特惠商品包装不能为空");
	      	_canpost = false;
	      	return false;
	    }
	}
	
	//秒杀验证
	if (_flag==6) {
		var _price=$("form:eq(" + index + ") input[name='price[]']").val();
		var _repertory =$("form:eq(" + index + ") input[name='repertory[]']").val();
		var _checkguige =$("form:eq(" + index + ") input.sellerMallNoStandardCheckBox:checked").val();
		if(!_price&&!_checkguige){
			layer.alert("秒杀单价不能为空");
			_canpost=false;
			return false;
		}
		if(!_repertory&&!_checkguige){
			layer.alert("秒杀库存不能为空");
	      	_canpost = false;
	      	return false;
		}
		var noStandardRepertory=$("form:eq(" + index + ") input[name='noStandardRepertory']").val();
		if(!noStandardRepertory &&!_repertory){
	      	layer.alert("秒杀库存不能为空");
	      	_canpost = false;
	      	return false;
	    }
		var noStandardPrice =$("form:eq(" + index + ") input[name='noStandardPrice']").val();
		if (!noStandardPrice&&!_price) {
			layer.alert("秒杀单价不能为空");
			_canpost=false;
			return false;
		}
		var displayPrice =$("form:eq(" + index + ") input[name='displayPrice']").val();
		if (!displayPrice) {
			layer.alert("秒杀商品原价不能为空");
			_canpost=false;
			return false;
		}

		var seckillCountLimit = $("form:eq(" + index +") input[name='seckillCountLimit']").val();
		if (!seckillCountLimit) {

			layer.alert("秒杀每人限购不能为空");
			_canpost=false;
			return false;
		}
		var seckillArea = $("form:eq(" + index +") input[name='seckillArea']").val();
		if (!seckillArea) {
			layer.alert("秒杀区域不能为空");
			_canpost=false;
			return false;
		}
		var transportationType = $("form:eq(" + index + ") input[name='transportationType']:checked").val();
    	var transportationPrice = $("form:eq(" + index + ") input[name='transportationPrice']").val();
    	 if(!transportationType){
 	      	layer.alert("秒杀配送方式不能为空");
 	      	_canpost = false;
 	      	return false;
 	    }
 	    if(transportationType==2){
 			  if(!transportationPrice){
 			      	layer.alert("秒杀邮费不能为空");
 			      	_canpost = false;
 			      	return false;
 			  }
 		 }
 	   var addedTime = $("form:eq(" + index + ") input[name='addedTime']").val();
 	   var shelvesTime = $("form:eq(" + index + ") input[name='shelvesTime']").val();
 	   if(!addedTime){
	      	layer.alert("秒杀上架时间不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    if(!shelvesTime){
	      	layer.alert("秒杀下架时间不能为空");
	      	_canpost = false;
	      	return false;
	    }
	    var times= $("form:eq(" + index +") input[name='times']").val();
	    if (!times) {
			layer.alert("秒杀时段不能为空");
			_canpost= false;
			return false;
		}
	}
	
	
	//商家商城验证
	if (_flag==7) {
		var _price=$("form:eq(" + index + ") input[name='price[]']").val();
		var _repertory =$("form:eq(" + index + ") input[name='repertory[]']").val();
		var _checkguige =$("form:eq(" + index + ") input.sellerMallNoStandardCheckBox:checked").val();
		if(!_price&&!_checkguige){
			layer.alert("商家商城单价不能为空");
			_canpost=false;
			return false;
		}
		if(!_repertory&&!_checkguige){
			layer.alert("商家商城库存不能为空");
	      	_canpost = false;
	      	return false;
		}
		var noStandardRepertory=$("form:eq(" + index + ") input[name='noStandardRepertory']").val();
		if(!noStandardRepertory&&!_repertory){
	      	layer.alert("商家商城库存不能为空");
	      	_canpost = false;
	      	return false;
	    }//da
		var noStandardPrice =$("form:eq(" + index + ") input[name='displayPrice']").val();
		if (!noStandardPrice&&!_price) {
			layer.alert("商家商城单价不能为空");
			_canpost=false;
			return false;
		}
		var displayPrice=$("form:eq(" + index + ") input[name='displayPrice']").val();
		if (!displayPrice) {
			layer.alert("商家商城市场价不能为空");
			_canpost=false;
			return false;
		}
		var transportationType = $("form:eq(" + index + ") input[name='transportationType']:checked").val();
		var transportationPrice = $("form:eq(" + index + ") input[name='transportationPrice']").val();
		 if(!transportationType){
		      	layer.alert("商家商城配送方式不能为空");
		      	_canpost = false;
		      	return false;
		    }
		    if(transportationType==2){
				  if(!transportationPrice){
				      	layer.alert("商家商城邮费不能为空");
				      	_canpost = false;
				      	return false;
				  }
			 }
		    var addedTime = $("form:eq(" + index + ") input[name='addedTime']").val();
		 	   var shelvesTime = $("form:eq(" + index + ") input[name='shelvesTime']").val();
		 	   if(!addedTime){
			      	layer.alert("商家商城上架时间不能为空");
			      	_canpost = false;
			      	return false;
			    }
			    if(!shelvesTime){
			      	layer.alert("商家商城下架时间不能为空");
			      	_canpost = false;
			      	return false;
			    }
		 
	}
}

