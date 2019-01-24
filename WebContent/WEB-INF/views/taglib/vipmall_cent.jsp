<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<div class="rows" id="CentCommission" >
			<label>会员商城分佣设置:</label>
			<div class="input">
				<table>
					<tr>
						<td>总部分佣比例:</td>
						<td><input type="text" style="width:50px" name="hqscale" id="hqScale" 
							value="${adminScale.hqscale*100 }" onchange="changeSum()"/>%&nbsp;&nbsp;</td>
						<td>运营中心分佣比例:</td>
						<td><input type="text" style="width:50px" name="centerScale" id="centerScale" 
							value="${adminScale.centerScale*100 }" onchange="changeSum()"/>%&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<!-- 
						<td>供应商分佣比例:</td>
						<td><input type="text" style="width:50px" name="adminScale.providerScale" id="providerScale"
							 value="${adminScale.providerScale*100 }" />%&nbsp;&nbsp;</td>
						 -->
						
						<td>
							<input type="hidden" style="width:50px" id="otherSellerScale" value="0"/>
							<input type="hidden" style="width:50px" id="providerScale" value="0"/>
							<input type="hidden" style="width:50px" name="sellerScale" id="sellerScale" value="0.0" />
							<input type="hidden" style="width:50px" name="providerScale" id="providerScale" value="${adminScale.providerScale*100 }" />
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
							一级比例:<input type="text" style="width:50px" onChange="setScale(1);changeSum();" 
										id="levelOneScaleText" value="" />%
						</td>
						<td>
							二级比例:<input type="text" style="width:50px" onChange="setScale(2);changeSum();"
										id="levelTwoScaleText" value="" />%
						</td>
						<td>
							三级比例:<input type="text" style="width:50px" onChange="setScale(3);changeSum();"
										id="levelThreeScaleText" value="" />%
						</td>
					</tr> 
					<c:forEach  items="${vipTypeList }" var="vipType" >
					<tr id="vipScaleTr">
							<td>
								<input type="hidden" style="width:50px" name="membersBonusList[${vipType.id }].levelThreeScale" 
											id="levelThreeScale" value="${vipScaleMap[vipType.id].levelThreeScale*100}" />
								<input type="hidden" style="width:50px" name="membersBonusList[${vipType.id }].levelTwoScale" 
											id="levelTwoScale" value="${vipScaleMap[vipType.id].levelTwoScale*100}" />
								<input type="hidden" style="width:50px" name="membersBonusList[${vipType.id }].levelOneScale" 
											id="levelOneScale" value="${vipScaleMap[vipType.id].levelOneScale*100}" />
								<input type="hidden" name="membersBonusList[${vipType.id }].id" id="vipScaleId" 
											value="${vipScaleMap[vipType.id].id}" />
								<input type="hidden" name="membersBonusList[${vipType.id }].isValid"
											value="${vipScaleMap[vipType.id].isValid}" />
								<input type="hidden" name="membersBonusList[${vipType.id }].providerId"
											value="${vipScaleMap[vipType.id].provider.id}" />
							</td>
					</tr>
					</c:forEach>
				</table>
				<table style="margin-top:20px;">
				<tr>
						<td>最终供应商分佣比例:</td>
						<td>
						<input type="text" readOnly="true" style="width:50px" name="providerScale" id="providerScaleFinal" value="100" />%
						&nbsp;&nbsp;</td>
					</tr>
				</table>
			</div>
			<div class="clear"></div>  
		</div>
