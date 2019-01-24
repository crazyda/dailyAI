<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<div class="rows" id="CentCommission" >
			<label>会员商城分佣设置:</label>
			<div class="input">
				<table>
					<tr>
						<td>平台分佣比例:</td>
						<td><input type="text" style="width:50px" name="hqscale" id="hqScale" 
							value="1" readOnly="true"/>%&nbsp;&nbsp;</td>
						<td>久久人分佣比例:</td>
						<td><input type="text" style="width:50px" name="centerScale" id="centerScale" 
							value="${adminScale.centerScale*100 }" onchange="changeSum()"/>%&nbsp;&nbsp;</td>
						
						<td>消费会员所属商家分佣比例:</td>
						<td><input type="text" style="width:50px" name="otherSellerScale" id="otherSellerScale" 
							value="${adminScale.otherSellerScale*100 }" onchange="changeSum()"/>%&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" style="width:50px" name="providerBonusId"  value="${adminScale.id }" />
							<input type="hidden" style="width:50px" name="providerBonusIsValid"  value="${adminScale.isValid }" />
						</td>
					</tr>
				</table>
				<table style="margin-top:20px;">
					<tr>
						<td>会员消费分佣比例:</td><td></td><td></td>
					</tr>
					<tr id="vipScaleTr">
						<td>
							返现比例:<input type="text" style="width:50px" onChange="setScale(1);changeSum();" 
										id="levelOneScaleText" value="" />%&nbsp;&nbsp;
						</td>
						<td>
							邀请人分佣比例:<input type="text" style="width:50px" onChange="setScale(2);changeSum();"
										id="levelTwoScaleText" value="" />%&nbsp;&nbsp;
						</td>
						<td><input type="hidden" style="width:50px" id="levelThreeScaleText" value="0" /></td>
					</tr> 
					<c:forEach  items="${vipTypeList }" var="vipType" >
					<tr id="vipScaleTr">
							
							<td>
								<input type="hidden" style="width:50px"  id="providerScale" value="0" />
								<input type="hidden" style="width:50px" name="membersBonusList[${vipType.id }].levelThreeScale" 
											id="levelThreeScale" value="0" />
								<input type="hidden" style="width:50px" name="membersBonusList[${vipType.id }].levelTwoScale" 
											id="levelTwoScale" value="${membersBonusList[vipType.id].levelTwoScale*100}" />
								<input type="hidden" style="width:50px" name="membersBonusList[${vipType.id }].levelOneScale" 
											id="levelOneScale" value="${membersBonusList[vipType.id].levelOneScale*100}" />
								<input type="hidden" name="membersBonusList[${vipType.id }].id" id="vipScaleId" 
											value="${membersBonusList[vipType.id].id}" />
								<input type="hidden" name="membersBonusList[${vipType.id }].isValid"
											value="${membersBonusList[vipType.id].isValid}" />
								<input type="hidden" name="membersBonusList[${vipType.id }].providerId"
											value="${membersBonusList[vipType.id].provider.id}" />
							</td>
					</tr>
					</c:forEach>
				</table>
				<table style="margin-top:20px;">
				<tr>
				<td>供应商分佣比例:</td>
						<td><input type="text" style="width:50px" name="providerScale" id="providerScaleFinal" 
							value="${adminScale.providerScale*100 } "/>%&nbsp;&nbsp;</td>
				</tr>
				
				</table>
			</div>
			<div class="clear"></div>  
		</div>
