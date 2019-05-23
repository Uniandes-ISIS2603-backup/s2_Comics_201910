

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;


import co.edu.uniandes.csw.comics.dtos.OrdenPedidoDTO;
import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jp.rodriguezv
 */
@Path("ordenesPedido")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class OrdenPedidoResource {
   
    @Inject
    OrdenPedidoLogic ordenPedidoLogic; //variable para acceder a la logica de la aplicacion
    
    private static final Logger LOGGER=Logger.getLogger(OrdenPedidoResource.class.getName());
    
    /**
     * Crea una nueva ordenPedido con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param ordenPedido {@link OrdenPedidoDTO} - La ordenPedido que se desea
     * guardar.
     * @return JSON {@link OrdenPedidoDTO} - La ordenPedido guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la ordenPedido.
     */
    @POST
    public OrdenPedidoDTO crearOrdenPedido (OrdenPedidoDTO ordenPedido) throws BusinessLogicException{
      //convierte el DTO (json) en un objeto entity para ser manejado por la logica
       LOGGER.log(Level.INFO, "Iniciando post ordenPedido"); 
      OrdenPedidoEntity ordenPedidoEntity = ordenPedido.toEntity();
        //invoca la logica para crear la nueva orden de pedido
         LOGGER.log(Level.INFO, "Iniciando create ordenesPedido");
         OrdenPedidoEntity nuevaOrdenPedidoEntity;
          LOGGER.log(Level.INFO, "Iniciando create ordenesPedido 1");
        Long idTrueque=null;
          if(ordenPedido.getTrueque()!=null){
               idTrueque= ordenPedido.getTrueque().getId();
          }
         nuevaOrdenPedidoEntity= ordenPedidoLogic.createOrdenPedido(ordenPedidoEntity, ordenPedido.getVendedor().getId(),ordenPedido.getComprador().getId(),ordenPedido.getComic().getId() , idTrueque );
          LOGGER.log(Level.INFO, "Iniciando create ordenesPedido 2");
        
        
        //como debe retornar un DTO (json) se invoca el contructor de DTO con argumento el entity nuevo
        OrdenPedidoDTO nuevaOrdenPedidoDTO= new OrdenPedidoDTO(nuevaOrdenPedidoEntity);
     String mensaje="se ha generado una nueva ordenPedido,"
             + " informacion de la nueva ordenPedido :  alias del comprador:" + nuevaOrdenPedidoDTO.getComprador().getAlias()+
             " nombre del comic: "+ nuevaOrdenPedidoDTO.getComic().getNombre()  ;
     if(nuevaOrdenPedidoDTO.getTrueque()!=null){
         mensaje= mensaje + " comic asociado para el trueque: " + nuevaOrdenPedidoDTO.getTrueque().getNombre();
     }
     else{
         mensaje= mensaje + " precio del comic: " + nuevaOrdenPedidoDTO.getComic().getPrecio();
     }
        enviarConGMail("Pietroehrlich@gmail.com", "creacion de nueva Orden", mensaje);
      return nuevaOrdenPedidoDTO;
              
    }
    
  
    
    /**
     * Busca la ordenPedido con el id asociado recibido en la URL y la devuelve.
     *
     * @param OrdenesPedidoId Identificador de la ordenPedido que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link OrdenPedidoDTO} - La ordenPedido buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la ordenPedido.
     */
   @GET
    @Path("{OrdenesPedidoId:\\d+}")
    public OrdenPedidoDTO getOrdenPedido (@PathParam("OrdenesPedidoId")Long OrdenesPedidoId) throws WebApplicationException
    {
        LOGGER.log(Level.INFO, "ComicDeseoResource findComicDeseoXId: input: {0}", OrdenesPedidoId);
      OrdenPedidoEntity entity = ordenPedidoLogic.getOrdenPedido(OrdenesPedidoId);
     if(entity == null){
     
         throw new WebApplicationException("El recurso/ordenesPedido/"+OrdenesPedidoId +"no existe.", 404);
         
     }
     OrdenPedidoDTO ordenPedidoDTO = new OrdenPedidoDTO(entity);
     LOGGER.log(Level.INFO, "ComicDeseoResource findComicDeseoXId: output: {0}", ordenPedidoDTO);
     return ordenPedidoDTO;
      
    }
    
   
    
    @GET
    @Path("{Estado}")
    public List<OrdenPedidoDTO> getOrdenesPedidoEstado ( @PathParam("Estado")OrdenPedidoEntity.Estado estado) throws WebApplicationException
    {
      List<OrdenPedidoDTO> listaordenesPedido = listEntity2DetailDTO(ordenPedidoLogic.getOrdenesPedido());
     List<OrdenPedidoDTO> lista2= new ArrayList<>();
  
        if(!(estado.equals(OrdenPedidoEntity.Estado.TRUEQUE)|| estado.equals(OrdenPedidoEntity.Estado.VENTA))){
       for(int i =0; i < listaordenesPedido.size(); i++){
         if( listaordenesPedido.get(i).getEstado().equals(estado)){
             lista2.add(listaordenesPedido.get(i));
         }
     }}
        else {
           for(int i =0; i < listaordenesPedido.size(); i++){
            if(estado.equals(OrdenPedidoEntity.Estado.VENTA)){
                 if( listaordenesPedido.get(i).getComic().getEnVenta()){
                lista2.add(listaordenesPedido.get(i));   
             }
         }
             if(estado.equals(OrdenPedidoEntity.Estado.TRUEQUE)){
                 if( !listaordenesPedido.get(i).getComic().getEnVenta()){
                lista2.add(listaordenesPedido.get(i));   
             }
         }
         } 
        }
        return lista2;
      
    }
    
    /**
     * Busca y devuelve todas las ordenesPedido que existen en la aplicacion.
     *
     * @return JSONArray {@link OrdenPedidoDTO} - Las ordenesPedido encontradas en
     * la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<OrdenPedidoDTO> getOrdenesPedido ()
    {
        List<OrdenPedidoDTO> listaordenesPedido = listEntity2DetailDTO(ordenPedidoLogic.getOrdenesPedido());
 
        return listaordenesPedido;
    }
    
     /**
     * Actualiza la ordenPedido con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param ordenesPedidoId Identificador de la ordenPedido que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param ordenPedido {@link OrdenPedidoDTO} La ordenPedido que se desea
     * guardar.
     * @return JSON {@link OrdenPedidoDetailDTO} - La ordenPedido guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la ordenPedido a
     * actualizar.
     */
    @PUT
    @Path("{ordenesPedidoId: \\d+}")
    public OrdenPedidoDTO updateOrdenPedido(@PathParam("ordenesPedidoId") Long ordenesPedidoId, OrdenPedidoDTO ordenPedido) throws WebApplicationException, BusinessLogicException {
        LOGGER.log(Level.INFO, "ordenPedidoResource updateOrdenPedido: input: id:{0} , editorial: {1}", new Object[]{ordenesPedidoId, ordenPedido});
        ordenPedido.setId(ordenesPedidoId);
        if (ordenPedidoLogic.getOrdenPedido(ordenesPedidoId) == null) {
            throw new WebApplicationException("El recurso /ordenes/" + ordenesPedidoId + " no existe.", 404);
        }
        String mensaje = "querido cliente, se ha actualizado el estado de su ordenPedido a " + ordenPedido.getEstado();
        
       enviarConGMail("Pietroehrlich@gmail.com", "cambio de estado orden", mensaje);
       
        OrdenPedidoDTO detailDTO = new OrdenPedidoDTO(ordenPedidoLogic.updateOrdenPedido(ordenesPedidoId, ordenPedido.toEntity()));
        LOGGER.log(Level.INFO, "OrdenPedidoResource updateOrdenPedido: output: {0}", detailDTO);
        return detailDTO;

    }
    
    /**
     * Borra la ordenPedido con el id asociado recibido en la URL.
     *
     * @param OrdenesPedidoId Identificador de la ordenPedido que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la ordenPedido.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la ordenPedido.
     */
     @DELETE
      @Path("{OrdenesPedidoId:\\d+}")
    public void deletOrdenPedido (@PathParam("OrdenesPedidoId")long OrdenesPedidoId) throws BusinessLogicException{
       {
         LOGGER.log(Level.INFO, "OrdenPedidoResource deleteOrdenPedido: input: {0}", OrdenesPedidoId);
        if (ordenPedidoLogic.getOrdenPedido(OrdenesPedidoId) == null) {
            throw new WebApplicationException("El recurso /authors/" + OrdenesPedidoId + " no existe.", 404);
        }
        ordenPedidoLogic.deleteOrdenPedido(OrdenesPedidoId);
        LOGGER.info("OrdenPedidoResource deleteOrdenPedido: output: void");
    }
       
    }
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos OrdenPedidoEntity a una lista de
     * objetos OrdenPedioDTO (json)
     *
     * @param entityList corresponde a la lista de ordenPedio de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de ordenPedido en forma DTO (json)
     */
    private List<OrdenPedidoDTO> listEntity2DetailDTO(List<OrdenPedidoEntity> entityList) {
        List<OrdenPedidoDTO> list = new ArrayList<>();
        for (OrdenPedidoEntity entity : entityList) {
            list.add(new OrdenPedidoDTO(entity));
        }
        return list;
    }
        private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
            System.out.println("llego hasta aqui");
    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
     String remitente = "bm.florez99@gmail.com";  //Para la dirección nomcuenta@gmail.com
     String clave = "60309268";
            System.out.println("llego hasta aqui 2");
    Properties props = System.getProperties();
    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
    props.put("mail.smtp.user", remitente);
    props.put("mail.smtp.clave", clave);    //La clave de la cuenta
    props.put("mail.smtp.auth", "true"); //Usar autenticación mediante usuario y clave
            System.out.println("llego hasta aqui 3");
   
    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
 System.out.println("llego hasta aqui 4");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
System.out.println("llego hasta aqui 5");
 
    try {
        message.setFrom(new InternetAddress(remitente));
        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
        message.setSubject(asunto);
        message.setText(cuerpo);
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", remitente, clave);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        System.out.println("en teoria deberia enviar el correo ");
    }
    catch (MessagingException me) {
        me.printStackTrace();   //Si se produce un error
    }
}
   
        public void SendMail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
  String remitente = "bm.florez99@gmail.com";  //Para la dirección nomcuenta@gmail.com
     String clave = "60309268";
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(remitente, clave);
                    }
                });
 
        try {
 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("jp.rodriguezv@uniandes.edu.co"));
            message.setSubject("prueba 1");
            message.setText("mensaje 1");
 
            Transport.send(message);
           
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}