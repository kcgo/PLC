package sample.tqi.com.br.planodecarreira.model.service;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;
import sample.tqi.com.br.planodecarreira.model.domain.AlterarStatusResponse;
import sample.tqi.com.br.planodecarreira.model.domain.Comentario;
import sample.tqi.com.br.planodecarreira.model.domain.Log;
import sample.tqi.com.br.planodecarreira.model.domain.StatusTalento;
import sample.tqi.com.br.planodecarreira.model.domain.StatusTutor;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;

/**
 * Created by alexandre.azevedo on 19/01/2018.
 */

public interface TarefaApi {

    @GET("avaliacao/{idModulo}/tarefas")
    Observable <List <Tarefa>> getListaTarefa( @Header("Authorization") String authorization,
                                               @Path("idModulo") int idModulo );

    @GET("avaliacao/{idTalento}/{idModulo}/tarefas")
    Observable <List <Tarefa>> getTutorListaTarefa( @Header("Authorization") String authorization,
                                                    @Path("idTalento") int idTalento,
                                                    @Path("idModulo") int idModulo );

    @GET("avaliacao/{idTalento}/{idModulo}/tarefas/{idTarefa}/visaogeral")
    Observable <Tarefa> getTarefaVisaoGeral( @Header("Authorization") String authorization,
                                             @Path("idTalento") int idTalento,
                                             @Path("idModulo") int idModulo,
                                             @Path("idTarefa") int idTarefa );

    @GET("http://demo5537570.mockable.io/planodecarreira/modulo/tarefa/visaogeral")
    Observable <Tarefa> getTarefaStatus();

    @GET("avaliacao/{idTalento}/{idModulo}/tarefas/{idTarefa}/logs")
    Observable <List <Log>> getTarefaLogStatus( @Header("Authorization") String authorization,
                                                @Path("idTalento") int idTalento,
                                                @Path("idModulo") int idModulo,
                                                @Path("idTarefa") int idTarefa );

    @GET("avaliacao/{idTalento}/{idModulo}/tarefas/{idTarefa}/comentarios")
    Observable <List <Comentario>> getTarefaComentario( @Header("Authorization") String authorization,
                                                        @Path("idTalento") int idTalento,
                                                        @Path("idModulo") int idModulo,
                                                        @Path("idTarefa") int idTarefa);

    @POST("avaliacao/{idTalento}/{idModulo}/tarefas/{idTarefa}/comentarios")
    Observable <Response <String>> postComentario( @Header("Authorization") String authorization,
                                                   @Body Comentario comentario,
                                                   @Path("idTalento") int idTalento,
                                                   @Path("idModulo") int idModulo,
                                                   @Path("idTarefa") int idTarefa);

    @PUT("avaliacao/{idTalento}/{idModulo}/tarefas/{idTarefa}")
    Observable<AlterarStatusResponse>putAlterarStatusTutor( @Header("Authorization") String authorization,
                                              @Body StatusTutor status,
                                              @Path("idTalento") int idTalento,
                                              @Path("idModulo") int idModulo,
                                              @Path("idTarefa") int idTarefa);

    @PUT("avaliacao/{idModulo}/tarefas/{idTarefa}")
    Observable<AlterarStatusResponse>putAlterarStatusTalento(@Header("Authorization") String authorization,
                                                             @Body StatusTalento status,
                                                             @Path("idModulo") int idModulo,
                                                             @Path("idTarefa") int idTarefa);

}