package App;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class WS_Controller {

    @Autowired
    private WS_Network net;

    @RequestMapping("/")
    public String index(){
        return "main_view";
    }

    @RequestMapping("/main_view")
    public String list(Model model){
        List<WeatherStation> data = new LinkedList<>();
        for (WeatherStation ws: net.findAll()){
            data.add(ws);
        }
        model.addAttribute("stations", data);
        return "main_view";
    }

    @RequestMapping("/integration_view")
    public String input(){
        return "integration_view";
    }

    @RequestMapping("/create")
    public String create(
            @RequestParam(name="Nation",required = true) String ws_nation,
            @RequestParam(name="Location", required=true) String ws_location,
            @RequestParam(name="Altitude", required = true) String ws_altitude) {
        if(ws_nation.isEmpty()|| ws_location.isEmpty())
            return "dataError";
        try {
            if(Double.parseDouble(ws_altitude)<=-30 ||Double.parseDouble(ws_altitude)>=8000)
                return "dataError";
            WeatherStation new_ws = new WeatherStation(ws_nation,ws_location,Double.parseDouble(ws_altitude));
            new_ws.initialize();
            net.save(new_ws);
            return "redirect:/main_view";
        }catch (NumberFormatException exception){
            return "dataError";
        }
    }

    @RequestMapping("/report_view")
    public String report_view(
            @RequestParam(name = "id",required = true) Long id, Model model){

        Optional<WeatherStation> result = net.findById(id);
        if (result.get()!= null){
            model.addAttribute("station", result.get());

        }
        else
            return "notfound";
        return "report_view";
    }

    @RequestMapping("/report")
    public String report(
            @RequestParam(name="op_name",required = true) String op_name,
            @RequestParam(name="op_surname",required = true) String op_surname,
            @RequestParam(name = "notes", required = true) String notes,
            @RequestParam(name="id", required= true) Long id,
            @RequestParam(name = "report", required = true) String report,
            Model model){
        Optional<WeatherStation> result = net.findById(id);
        if (result.get()!= null){
            if(op_name.isEmpty() || op_surname.isEmpty() || report.isEmpty())
                return "dataError";
            if(notes.isEmpty())
                result.get().addReport(op_name,op_surname,report);
            else
                result.get().addReport(op_name, op_surname,report,notes);
            WeatherStation new_ws = result.get();
            net.deleteById(id);
            net.save(new_ws);
        }
        else
            return "notfound";

        return "redirect:/main_view";
    }

    @RequestMapping("/detail_view")
    public String detail_view(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<WeatherStation> result = net.findById(id);
        if (result.get()!= null) {

            result.get().ws_new_infos();
            WeatherStation new_ws = result.get();
            new_ws.setCode(result.get().getCode());
            net.deleteById(id);
            net.save(new_ws);
            model.addAttribute("station", new_ws);
        }
        else
            return "notfound";
        return "detail_view";
    }


    @RequestMapping("/disable_ws_view")
    public String disable_view(
            @RequestParam(name="id", required= true) Long id,
            Model model){
        Optional<WeatherStation> result = net.findById(id);
        if(result.get()!= null)
            model.addAttribute("station", result.get());
        else
            return "notfound";
        return "disable_ws_view";
    }


    @RequestMapping("/disable")
    public String disable(
            @RequestParam(name="op_name",required = true) String op_name,
            @RequestParam(name="op_surname",required = true) String op_surname,
            @RequestParam(name = "reason", required = true) String reason,
            @RequestParam(name="id", required= true) Long id,
            Model model){
        Optional<WeatherStation> result = net.findById(id);
        if (result.get()!= null){
            if(reason.isEmpty() || op_name.isEmpty() || op_surname.isEmpty())
                return "dataError";

            result.get().setWs_status(ColorCode.GREY);
            result.get().addReport(op_name, op_surname,"DISABLED-"+ reason);
            WeatherStation new_ws = result.get();
            new_ws.setDisabled(true);
            net.deleteById(id);
            net.save(new_ws);
        }
        else
            return "notfound";

        return "redirect:/main_view";
    }

    @RequestMapping("/reactivate_ws_view")
    public String reactivate_view(
            @RequestParam(name="id", required= true) Long id,
            Model model){

        Optional<WeatherStation> result = net.findById(id);
        if (result.get()!= null){
            model.addAttribute("station", result.get());
        }
        else
            return "notfound";
        return "reactivate_ws_view";
    }

    @RequestMapping("/reactivate")
    public String reactivate(
            @RequestParam(name="id", required= true) Long id,
            Model model){
        Optional<WeatherStation> result = net.findById(id);
        if(result.get()!=null){
            result.get().setWs_status(ColorCode.GREEN);
            result.get().addReport("REACTIVATED");
            WeatherStation new_ws = result.get();
            new_ws.setDisabled(false);
            net.deleteById(id);
            net.save(new_ws);
        }
        else
            return "notfound";
        return "redirect:/main_view";
    }


    @RequestMapping("/delete_ws_view")
    public String delete_view(
            @RequestParam(name="id", required= true) Long id,
            Model model){

        Optional<WeatherStation> result = net.findById(id);
        if (result.get()!= null){
            model.addAttribute("station", result.get());
        }
        else
            return "notfound";
        return "delete_ws_view";
    }


    @RequestMapping("/delete")
    public String delete(
            @RequestParam(name="id", required=true) Long id) {
        Optional<WeatherStation> result = net.findById(id);
        if(result!=null)
            net.deleteById(id);
        else
            return "notfound";

        return "redirect:/main_view";
    }

}