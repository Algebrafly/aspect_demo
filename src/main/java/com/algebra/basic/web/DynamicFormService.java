//package com.algebra.basic.web;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.lunz.fin.base.ServiceBase;
//import com.lunz.fin.entity.dynamicForm.DynamicForm;
//import com.lunz.fin.entity.dynamicForm.DynamicFormDetail;
//import com.lunz.fin.interfaces.IDynamicFormDetailService;
//import com.lunz.fin.interfaces.IDynamicFormService;
//import com.lunz.fin.mapper.DynamicFormMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//public class DynamicFormService extends ServiceBase<DynamicFormMapper, DynamicForm> implements IDynamicFormService {
//
//    @Autowired
//    IDynamicFormDetailService dynamicFormDetailService;
//
//    @Override
//    public String GetDynamicForm(DynamicForm model) {
//
//        log.debug("开始获取DynamicForm");
//
//        EntityWrapper<DynamicForm> wrapper=new EntityWrapper<>();
//        wrapper.eq("clientId",model.getClientId());
//
//        if(!model.getModel().equals(null) &&!model.getModel().isEmpty()){
//            wrapper.eq("model",model.getModel());
//        }
//
//        if(!model.getModelGroup().equals(null) &&!model.getModelGroup().isEmpty()){
//            wrapper.eq("modelGroup",model.getModelGroup());
//        }
//        if(model.getCurrentStatusID()!=0){
//
//            if(model.getCurrentStatusID()==-1){
//                model.setCurrentStatusID(0);
//            }
//            wrapper.andNew();
//            wrapper.isNull("HideStatusIDs");
//            wrapper.or();
//            wrapper.notLike("HideStatusIDs",String.format("%%,%s,%%",model.getCurrentStatusID()));
//        }
//
//        wrapper.orderBy("modelOrder");
//
//        List<DynamicForm> lstForms=this.baseMapper.selectList(wrapper);
//
//        if(lstForms==null || lstForms.isEmpty()){
//            return null;
//        }
//
//        List<Long> lstIds=lstForms.stream().map(r->r.getId()).collect(Collectors.toList());
//        List<DynamicFormDetail> lstFormDetail=dynamicFormDetailService.GetDynamicFormDetails(lstIds.toArray(new Long[lstIds.size()]));
//
//        List<Object> lstMap=new ArrayList<>();
//        for (DynamicForm form : lstForms) {
//
//            List<DynamicFormDetail> lstCurrentFormDetail=
//                    lstFormDetail.stream().filter(r->r.getDynamicId().equals(form.getId())).collect(Collectors.toList());
//
//            if(model.getIsDetail()!=null && model.getIsDetail()){
//                for(DynamicFormDetail detail:lstCurrentFormDetail){
//                    detail.setIsDisabled(true);
//                }
//            }else{
//
//                if(model.getCurrentStatusID()!=null && model.getCurrentStatusID()>0){
//                    /**
//                     * The property of IsDisabled will be set as true if
//                     * 1: IsDisabled=true
//                     * 2: Current StatusID is in ReadonlyIDs
//                     */
//                    for(DynamicFormDetail detail:lstCurrentFormDetail){
//
//                        if(detail.getIsDisabled()==false){
//
//                            if(detail.getReadonlyStatusIDs()!=null){
//                                if(detail.getReadonlyStatusIDs().indexOf(String.format(",%s,",model.getCurrentStatusID()))>=0){
//                                    detail.setIsDisabled(true);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            String strJson= JSON.toJSONString(form);
//            HashMap<String,Object> hashmap=JSON.parseObject(strJson,HashMap.class);
//            hashmap.put(form.getModel(),JSON.toJSON(lstCurrentFormDetail));
//
//            lstMap.add(hashmap);
//        }
//
//        return JSON.toJSONString(lstMap);
//    }
//}
