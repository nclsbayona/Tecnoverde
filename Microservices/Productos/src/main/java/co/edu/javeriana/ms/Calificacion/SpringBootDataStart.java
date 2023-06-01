package co.edu.javeriana.ms.Calificacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.javeriana.ms.Calificacion.entities.InfoAdicional;
import co.edu.javeriana.ms.Calificacion.entities.MultimediaXInfo;
import co.edu.javeriana.ms.Calificacion.entities.Pregunta;
import co.edu.javeriana.ms.Calificacion.entities.Contenido;
import co.edu.javeriana.ms.Calificacion.entities.Respuesta;
import co.edu.javeriana.ms.Calificacion.entities.Servicio;
import co.edu.javeriana.ms.Calificacion.entities.TipoInformacion;
import co.edu.javeriana.ms.Calificacion.entities.TipoContenido;
import co.edu.javeriana.ms.Calificacion.entities.TipoServicio;
import co.edu.javeriana.ms.Calificacion.repositories.ContenidoRepository;
import co.edu.javeriana.ms.Calificacion.repositories.InfoAdicionalRepository;
import co.edu.javeriana.ms.Calificacion.repositories.MultimediaXInfoRepository;
import co.edu.javeriana.ms.Calificacion.repositories.PreguntaRepository;
import co.edu.javeriana.ms.Calificacion.repositories.RespuestaRepository;
import co.edu.javeriana.ms.Calificacion.repositories.ServicioRepository;
import co.edu.javeriana.ms.Calificacion.repositories.TipoContenidoRepository;
import co.edu.javeriana.ms.Calificacion.repositories.TipoInformacionRepository;
import co.edu.javeriana.ms.Calificacion.repositories.TipoServicioRepository;

@Configuration
class SpringBootDataStart {

    @Autowired
    private ServicioRepository serviciosRepository;

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private TipoContenidoRepository tipoContenidoRepository;

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    @Autowired
    private InfoAdicionalRepository infoAdicionalRepository;

    @Autowired
    private TipoInformacionRepository tipoInformacionRepository;

    @Autowired
    private MultimediaXInfoRepository multimediaXInfoRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Bean
    CommandLineRunner startCommandLineRunner(){
        return args -> {
            System.out.println("Spring Boot started correctly!");
        };
    }

    @Bean
    CommandLineRunner initTiposServicio(){
        return args -> {
            TipoServicio t=new TipoServicio();
            t.setTipo("Comida");
            tipoServicioRepository.save(t);

            t=new TipoServicio();
            t.setTipo("Hospedaje");
            tipoServicioRepository.save(t);

            t=new TipoServicio();
            t.setTipo("Acompañamiento");
            tipoServicioRepository.save(t);

            t=new TipoServicio();
            t.setTipo("Transporte");
            tipoServicioRepository.save(t);
        };
    }

    @Bean
    CommandLineRunner initServicios(){
        return args -> {
            Servicio s=new Servicio();
            s.setNombre("Acompañamiento a explorar");
            s.setDescripcion("La idea del servicio es que te pueda acompañar a explorar, así no estarás solo. Soy toda una aventurera y será para mi un gusto acompañarte :D");
            s.setProveedor_ID("2");
            s.setTipoServicio(tipoServicioRepository.findByTipo("Acompañamiento"));
            serviciosRepository.save(s);

            s=new Servicio();
            s.setNombre("Alojamiento 5 estrellas");
            s.setDescripcion("Alojamiento 5 estrellas con vista a una hermosa cascada");
            s.setProveedor_ID("1");
            s.setTipoServicio(tipoServicioRepository.findByTipo("Hospedaje"));
            serviciosRepository.save(s);

            s=new Servicio();
            s.setNombre("Comida típica");
            s.setDescripcion("Comida típica de la región, con ingredientes frescos y de la mejor calidad");
            s.setProveedor_ID("2");
            s.setTipoServicio(tipoServicioRepository.findByTipo("Comida"));
            serviciosRepository.save(s);
        };
    }

    @Bean
    CommandLineRunner initTiposContenido(){
        return args -> {
            TipoContenido t=new TipoContenido();
            t.setTipo("Noticia");
            tipoContenidoRepository.save(t);

            t=new TipoContenido();
            t.setTipo("Infografia");
            tipoContenidoRepository.save(t);

            t=new TipoContenido();
            t.setTipo("Video");
            tipoContenidoRepository.save(t);

            t=new TipoContenido();
            t.setTipo("Imagen");
            tipoContenidoRepository.save(t);
        };
    }

    @Bean
    CommandLineRunner initContenidos(){
        return args -> {
            Contenido p=new Contenido();
            p.setNombre("Publicando ando");
            p.setDescripcion("Aquí haciendo una publicación. https://st3.depositphotos.com/3102403/14911/v/950/depositphotos_149113985-stock-illustration-spring-nature-infographics.jpg");
            p.setProveedor_ID("1");
            p.setTipoContenido(tipoContenidoRepository.findByTipo("Infografia"));
            contenidoRepository.save(p);

            p=new Contenido();
            p.setNombre("Con Vero exploramos");
            p.setDescripcion("Siempre acompañandote en las buenas y en las malas. https://www.youtube.com/watch?v=cElhIDdGz7M");
            p.setProveedor_ID("2");
            p.setTipoContenido(tipoContenidoRepository.findByTipo("Video"));
            contenidoRepository.save(p);

            p=new Contenido();
            p.setNombre("RPC");
            p.setDescripcion("RPC es un protocolo de comunicación que permite a un programa de computadora llamar a otro sin conocer los detalles de la red. https://medium.com/turkcell/grpc-implementation-with-spring-boot-7d6f98349d27");
            p.setProveedor_ID("2");
            p.setTipoContenido(tipoContenidoRepository.findByTipo("Noticia"));
            contenidoRepository.save(p);
       };
    }

    @Bean
    CommandLineRunner initTipoInformacion(){
        return args -> {
            TipoInformacion t=new TipoInformacion();
            t.setTipo("Pagina web");
            tipoInformacionRepository.save(t);

            t=new TipoInformacion();
            t.setTipo("Horarios");
            tipoInformacionRepository.save(t);

            t=new TipoInformacion();
            t.setTipo("Video promocional");
            tipoInformacionRepository.save(t);

            t=new TipoInformacion();
            t.setTipo("Infografia");
            tipoInformacionRepository.save(t);

            t=new TipoInformacion();
            t.setTipo("Imagen");
            tipoInformacionRepository.save(t);
        };
    }

    @Bean
    CommandLineRunner initInfoAdicional(){
        return args -> {
            InfoAdicional i=new InfoAdicional();
            i.setTipoInformacion(tipoInformacionRepository.getReferenceById("Pagina web"));
            i.setValor("https://medium.com/turkcell/grpc-implementation-with-spring-boot-7d6f98349d27");
            infoAdicionalRepository.save(i);

            i=new InfoAdicional();
            i.setTipoInformacion(tipoInformacionRepository.getReferenceById("Horarios"));
            i.setValor("Lunes a Viernes: 7AM-5PM, Sábados: 10AM-2PM, Domingos y Festivos: 11-1PM");
            infoAdicionalRepository.save(i);

            i=new InfoAdicional();
            i.setTipoInformacion(tipoInformacionRepository.getReferenceById("Video promocional"));
            i.setValor("https://www.youtube.com/watch?v=Z1ktxiqyiLA");
            infoAdicionalRepository.save(i);

            i=new InfoAdicional();
            i.setTipoInformacion(tipoInformacionRepository.getReferenceById("Video promocional"));
            i.setValor("https://www.youtube.com/watch?v=cElhIDdGz7M");
            infoAdicionalRepository.save(i);

            i=new InfoAdicional();
            i.setTipoInformacion(tipoInformacionRepository.getReferenceById("Infografia"));
            i.setValor("https://st3.depositphotos.com/3102403/14911/v/950/depositphotos_149113985-stock-illustration-spring-nature-infographics.jpg");
            infoAdicionalRepository.save(i);
        };
    }

    @Bean
    CommandLineRunner initMultimediaXInfo(){
        return args -> {
            MultimediaXInfo m=new MultimediaXInfo();
            m.setInfoAdicional(infoAdicionalRepository.getReferenceById(1L));
            m.setProducto(contenidoRepository.getReferenceById(1L));
            multimediaXInfoRepository.save(m);

            m=new MultimediaXInfo();
            m.setInfoAdicional(infoAdicionalRepository.getReferenceById(2L));
            m.setProducto(serviciosRepository.getReferenceById(1L));
            multimediaXInfoRepository.save(m);

            m=new MultimediaXInfo();
            m.setInfoAdicional(infoAdicionalRepository.getReferenceById(1L));
            m.setProducto(contenidoRepository.getReferenceById(3L));
            multimediaXInfoRepository.save(m);

            m=new MultimediaXInfo();
            m.setInfoAdicional(infoAdicionalRepository.getReferenceById(2L));
            m.setProducto(serviciosRepository.getReferenceById(4L));
            multimediaXInfoRepository.save(m);

            m=new MultimediaXInfo();
            m.setInfoAdicional(infoAdicionalRepository.getReferenceById(3L));
            m.setProducto(contenidoRepository.getReferenceById(3L));
            multimediaXInfoRepository.save(m);

            m=new MultimediaXInfo();
            m.setInfoAdicional(infoAdicionalRepository.getReferenceById(4L));
            m.setProducto(serviciosRepository.getReferenceById(4L));
            multimediaXInfoRepository.save(m);

            m=new MultimediaXInfo();
            m.setInfoAdicional(infoAdicionalRepository.getReferenceById(5L));
            m.setProducto(contenidoRepository.getReferenceById(3L));
            multimediaXInfoRepository.save(m);
        };
    }

    @Bean
    CommandLineRunner initPreguntas(){
        return args -> {
            Pregunta p=new Pregunta();
            p.setCliente_ID("2");
            p.setProducto(contenidoRepository.getReferenceById(4L));
            p.setPregunta("¿Por qué los desarrolladores de blockchain son tan malos en los deportes?");
            preguntaRepository.save(p);

            p=new Pregunta();
            p.setCliente_ID("2");
            p.setProducto(contenidoRepository.getReferenceById(3L));
            p.setPregunta("¿Puede la comunicación basada en RPC ser considerada una forma de diálogo genuino, o se trata simplemente de una simulación de diálogo, sin que realmente haya una comunicación auténtica entre las partes involucradas?");
            preguntaRepository.save(p);

            /* ¿Por qué SOAP, REST y RPC están celosos de Blockchain?
            Porque Blockchain tiene una cadena de bloques, mientras que ellos solo tienen una cadena de caracteres.*/        
        };
    }

    @Bean
    CommandLineRunner initRespuestas(){
        return args -> {
            Respuesta r=new Respuesta();
            r.setPregunta(preguntaRepository.findAllById(1L));
            r.setTexto("Porque siempre se quedan atrapados en el bloque génesis. xDxD");
            respuestaRepository.save(r);
        };
    }

    @Bean
    CommandLineRunner sayCommandLineRunner(){
        return args -> {
            System.out.println("Spring Boot ready!");
        };
    }
}



