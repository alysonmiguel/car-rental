package tads.eaj.br.locadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CarroController {

    CarroService service;

    @Autowired
    public void setService(CarroService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String getHome(Model model, HttpServletResponse response, @CookieValue(value = "ultimaVez", defaultValue = "Primeiro acesso") String ultimaVez) {
        List<Carro> carroList = service.findAll();
        model.addAttribute(carroList);

        model.addAttribute("ultimaVez", ultimaVez);

        Date d = new Date();
        DateFormat dataformatada = new SimpleDateFormat("HH:mm:ss_dd-MM-yyyy");
        String data = dataformatada.format(d);
        Cookie c = new Cookie("ultimaVez", data);

        response.addCookie(c);

        return "home.html";
    }

    @RequestMapping("/cadastrar")
    public  String getCadastrar(Model model){
        Carro c = new Carro();
        model.addAttribute("novoCarro", c);
        return "cadastrar";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String doSalvar(@ModelAttribute (name = "novoCarro") @Valid Carro carro, Errors errors, RedirectAttributes r){
        if(errors.hasErrors()){
            return "cadastrar";
        }else {
            r.addFlashAttribute("sucesso", true);
            service.save(carro); ;
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/atualizar", method = RequestMethod.POST)
    public String doAtualizar(@ModelAttribute (name = "carro") @Valid Carro carro, Errors errors, RedirectAttributes r){
        if(errors.hasErrors()){
            return "editar";
        }else {
            r.addFlashAttribute("atualizou", true);
            service.save(carro); ;
            return "redirect:/";
        }
    }

    @RequestMapping("/editar/{id}")
    public ModelAndView getEditar(@PathVariable (name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView( "editar");
        Carro c = service.getById(id);
        modelAndView.addObject("carro", c );
        return modelAndView;
    }
    @RequestMapping("/deletar/{id}")
    public String doDelete(@PathVariable(name = "id") Long id, RedirectAttributes r){
        r.addFlashAttribute("apagou", true);
        service.delete(id);
        return "redirect:/";
    }

}
