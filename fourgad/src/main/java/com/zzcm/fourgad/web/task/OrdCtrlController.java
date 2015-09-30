package com.zzcm.fourgad.web.task;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zzcm.fourgad.entity.CreateOrdCtrl;
import com.zzcm.fourgad.service.account.ShiroDbRealm.ShiroUser;
import com.zzcm.fourgad.service.task.CrtOrdService;

/**
 * crtOrd管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /crtOrd/
 * Create page : GET /crtOrd/create
 * Create action : POST /crtOrd/create
 * Update page : GET /crtOrd/update/{id}
 * Update action : POST /crtOrd/update
 * Delete action : GET /crtOrd/delete/{id}
 * 
 * @author wancan
 */
@Controller
@RequestMapping(value = "/ordCtrl")
public class OrdCtrlController {

	@Autowired
	private CrtOrdService crtOrdService;
	
	@RequestMapping(value = "updateStatus/{id}/{status}", method = RequestMethod.GET)
	public String updateStatus(@PathVariable("id") Long id,@PathVariable("status") Integer status,RedirectAttributes redirectAttributes) {
		crtOrdService.updateStatus(status, id);
		CreateOrdCtrl ordCtrl = crtOrdService.getCrtOrdCtrlById(id);
		//重置对应的任务开关
		crtOrdService.resetCrtOrdList(ordCtrl.getOrdKey(),ordCtrl.getCrtOrdStatus());
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/task/";
	}
	
	@RequestMapping(value = "refureshTasks", method = RequestMethod.GET)
	public String refureshTasks(RedirectAttributes redirectAttributes) {
		crtOrdService.initOrdCtrlList();
		redirectAttributes.addFlashAttribute("message", "刷新成功");
		return "redirect:/task/";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("ordCtrl", new CreateOrdCtrl());
		model.addAttribute("action", "create");
		return "task/ordCtrlForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid CreateOrdCtrl ordCtrl, RedirectAttributes redirectAttributes) {
		crtOrdService.saveCrtOrdCtrl(ordCtrl.getOrdTitle(), ordCtrl.getOrdKey());
		redirectAttributes.addFlashAttribute("message", "创建订单生成控制成功");
		return "redirect:/task/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ordCtrl", crtOrdService.getCrtOrdCtrlById(id));
		model.addAttribute("action", "update");
		return "task/ordCtrlForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("ordCtrl") CreateOrdCtrl ordCtrl, RedirectAttributes redirectAttributes) {
		crtOrdService.updateCrtOrdCtrl(ordCtrl.getOrdTitle(), ordCtrl.getOrdKey(), ordCtrl.getId());;
		redirectAttributes.addFlashAttribute("message", "更新订单生成控制成功");
		return "redirect:/task/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		crtOrdService.deleteCrtOrdCtrl(id);
		crtOrdService.initOrdCtrlList();
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/task/";
	}

	@RequestMapping(value = "refureshOrdCtrl", method = RequestMethod.GET)
	public String refureshOrdCtrl(RedirectAttributes redirectAttributes) {
		crtOrdService.initOrdCtrlList();
		redirectAttributes.addFlashAttribute("message", "刷新成功");
		return "redirect:/task/";
	}
	
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
}
