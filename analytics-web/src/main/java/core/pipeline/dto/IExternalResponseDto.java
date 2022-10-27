package core.pipeline.dto;

/**
 * Created by istvolov on 20.06.18.
 */
public interface IExternalResponseDto {

    boolean isSuccessResponse();

    int getStatusCode();

    String getResponseMessage();

    String getMethodTitle();

}
