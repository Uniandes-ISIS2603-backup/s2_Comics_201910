/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.ComicDetailDTO;
import co.edu.uniandes.csw.comics.ejb.VendedorComicLogic;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VendedorComicResource {
     private static final Logger LOGGER = Logger.getLogger(VendedorComicResource.class.getName());
@Inject 
private VendedorComicLogic vendedorComicLogic;

   @POST
    @Path("{comicId: \\d+}")
    public ComicDetailDTO addComic(@PathParam("vendedoresId") Long vendedoresId, @PathParam("comicsId") Long comicsId) {
        LOGGER.log(Level.INFO, "VendedorComicResource addBook: input: authorsId {0} , booksId {1}", new Object[]{vendedoresId, comicsId});
    //    if (comicLogic.getBook(comicId) == null) {
       //     throw new WebApplicationException("El recurso /books/" + comicId + " no existe.", 404);
     //   }
   //     ComicDetailDTO detailDTO = new ComicDetailDTO(vendedorComicLogic.addBook(vendedorId, comicId));
     //   LOGGER.log(Level.INFO, "AuthorBooksResource addBook: output: {0}", detailDTO);
        return null;
    }
    @GET
    public List<ComicDetailDTO> getComics(@PathParam("vendedoresId") Long vendedoresId) {
        LOGGER.log(Level.INFO, "VendedorComicResource getBooks: input: {0}", vendedoresId);
     //   List<ComicDetailDTO> lista = booksListEntity2DTO(vendedorComicLogic.getBooks(vendedorId));
      //  LOGGER.log(Level.INFO, "VendedorComicResource getBooks: output: {0}", lista);
        return null;
    }
      @GET
    @Path("{comicId: \\d+}")
    public ComicDetailDTO getComic(@PathParam("vendedoresId") Long vendedoresId, @PathParam("comicsId") Long comicsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VendedorComicResource getBook: input: authorsId {0} , booksId {1}", new Object[]{vendedoresId, comicsId});
     //   if (bookLogic.getBook(booksId) == null) {
      //      throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
     //   }
      //  BookDetailDTO detailDTO = new BookDetailDTO(authorBookLogic.getBook(authorsId, booksId));
      //  LOGGER.log(Level.INFO, "AuthorBooksResource getBook: output: {0}", detailDTO);
        return null;
    }
     @PUT
    public List<ComicDetailDTO> replaceComics(@PathParam("vendedoresId") Long vendedorId, List<ComicDetailDTO> comics) {
        LOGGER.log(Level.INFO, "AuthorBooksResource replaceBooks: input: authorsId {0} , books {1}", new Object[]{vendedorId, comics});
        for (ComicDetailDTO comic : comics) {
        //    if (bookLogic.getBook(book.getId()) == null) {
         //       throw new WebApplicationException("El recurso /books/" + book.getId() + " no existe.", 404);
        //    }
        }
      //  List<ComicDetailDTO> lista = booksListEntity2DTO(authorBookLogic.replaceBooks(authorsId, booksListDTO2Entity(books)));
      //  LOGGER.log(Level.INFO, "AuthorBooksResource replaceBooks: output: {0}", lista);
        return null;
    }
       @DELETE
    @Path("{comicsId: \\d+}")
    public void removeComic(@PathParam("vendedoresId") Long vendedoresId, @PathParam("comicsId") Long comicsId) {
        LOGGER.log(Level.INFO, "AuthorBooksResource deleteBook: input: authorsId {0} , booksId {1}", new Object[]{vendedoresId, comicsId});
      //  if (bookLogic.getBook(booksId) == null) {
       //     throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
      //  }
       // authorBookLogic.removeBook(authorsId, booksId);
       // LOGGER.info("AuthorBooksResource deleteBook: output: void");
    }
  //   private List<ComicDetailDTO> comicListEntity2DTO(List<ComicEntity> entityList) {
   //     List<BookDetailDTO> list = new ArrayList<>();
   //     for (BookEntity entity : entityList) {
   //         list.add(new BookDetailDTO(entity));
  //      }
  //      return list;
  //  }
  //  private List<BookEntity> booksListDTO2Entity(List<BookDetailDTO> dtos) {
  //      List<BookEntity> list = new ArrayList<>();
  //      for (BookDetailDTO dto : dtos) {
   //         list.add(dto.toEntity());
   //     }
   //     return list;
   // }
}
