package service;

import org.brotli.dec.BrotliInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public abstract class AbstractParser {

    protected final HttpClient client;

    public AbstractParser() {
        client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public Map<String, String> getHeaders() {
        Map<String, String> result = new HashMap<>();

        result.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        result.put("accept-encoding", "gzip, deflate, br");
        result.put("accept-language", "ru-RU,ru;q=0.9");
        result.put("sec-ch-ua", "\".Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"103\", \"Chromium\";v=\"103\"");
        result.put("sec-ch-ua-mobile", "?0");
        result.put("sec-ch-ua-platform", "\"Windows\"");
        result.put("sec-fetch-dest", "document");
        result.put("sec-fetch-mode", "navigate");
        result.put("sec-fetch-site", "none");
        result.put("sec-fetch-user", "?1");
        result.put("upgrade-insecure-requests", "1");
        result.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        // result.put("cookie", "u=2ou9ky7e.1culpi9.j9j0u7zgf700; _ym_uid=1627842930461713236; __gads=ID=cd00c08ea01a3ccd:T=1627842935:S=ALNI_MZdmSvk-RHQX4p-pmMx4RtIdLTzXA; buyer_index_tooltip=1; buyer_laas_location=644200; _ym_d=1644911427; lastViewingTime=1646131178481; showedStoryIds=136-133-111-135-124-129-134-132-131-128-125-121-122-120-116-115-112-104-103-99-94; _gcl_au=1.1.1463779507.1654793491; tmr_lvid=ddf6175ba2e03886b52e02cea9cf9635; tmr_lvidTS=1656850377614; buyer_location_id=644200; _gid=GA1.2.1929561240.1658686630; luri=perm; f=5.0c4f4b6d233fb90636b4dd61b04726f14f0aa6d4f7157ca44f0aa6d4f7157ca44f0aa6d4f7157ca44f0aa6d4f7157ca42668c76b1faaa3582668c76b1faaa3582668c76b1faaa3584f0aa6d4f7157ca402b7af2c19f2d05c02b7af2c19f2d05c0df103df0c26013a0df103df0c26013a2ebf3cb6fd35a0ac0df103df0c26013a8b1472fe2f9ba6b9e2bfa4611aac769efa4d7ea84258c63d59c9621b2c0fa58f915ac1de0d034112ad09145d3e31a56946b8ae4e81acb9fae2415097439d4047fb0fb526bb39450a46b8ae4e81acb9fa34d62295fceb188dd99271d186dc1cd03de19da9ed218fe2d50b96489ab264edd50b96489ab264edd50b96489ab264ed46b8ae4e81acb9fa38e6a683f47425a8352c31daf983fa077a7b6c33f74d335cb88de1666d503ec67ea2e916b717064d03f2e1a870b7803bcf3741cdfe9f405700d1020250e32e7817c7721dca45217bbc6af755bd754674d1f68114840cb39be2415097439d404746b8ae4e81acb9fa786047a80c779d5146b8ae4e81acb9fabbae92eef83536e0b6c9122eda0b0e572da10fb74cac1eabb3ae333f3b35fe91de6c39666ae9b0d7312f8fecc8ca5e543486a07687daa291; ft=\"wCC+dBePLBATtnkMFTqcg1mTlkAjuD3toSZ9jsWsc+YTxhpioiLE1YH1uNFWRG/xHQU0eFZ5FQu8okzTW8MlVqMXJQz+Nh9FoThAWBt3MhQsGZtgUb3LC8EZCHhh+BMj7bOF4tXZKnIAjiFOycgzLyaQBblCRIEWwAKyf71WMnmkyUbAWee2bvgP1DqUpO5R\"; _ym_isad=1; v=1658858553; abp=1; _ym_visorc=b; sx=H4sIAAAAAAACA51W2a7juA78l%2FM8DWihFvbfSKJkO%2FG%2BJLYH%2Fe%2BXPkAyffJ4EyAwgqRYKhaL%2BvdLKFukjlapmNB7baQkMjoJBO%2FRwdfvf78eX7%2B%2Fdr%2FWKe8dqZEm7abce9Sb1MvTnqu4f%2F3zlb9%2BS2s8KglG%2F%2FnnS9hovCUvlMSUJQRI2euQiidNwqs3cmrTFlPa96YNMRo9R4d5yTPsz93Iv5AlGnQXcvKYAcgBJK2EU0IDEqGRMYIyb%2BTwHKFO0vWH7lS9i1EXum1HmNBXy2h%2FInvPyFJKUfiRgpRKSSty0KbwQYwMzjjxQi7n1lcEd%2BNaGOgJe3fER7M9EI9BY%2FVTDbjUkNpubXrYsHWDF8%2BqWobkYRleiOBSAEz0ywD6XyCs%2F8VHkr8IY1ElyyL4XH%2FDGvUNWyhnLb3NxqEuQJb495ISSeQv31IsRjsMpF2%2F0zQ%2B63z03tVLMyuvRrn%2BJOwFIyuTQmQu2lmLTnLPqGTS3jtZjEtvY5zKTm0P0az3edF7JfPcHLewsmran%2FATGS%2BRVcjOYdFConIuWRQCleBySlimXV7ImYbD9GRnPd5EP5Yhd7etFtUsxDCW44PzZQydi9DsX7Alo%2BZyqpBKQQqLSSSlX8jrLe21n%2FPx3HBP22FOWWrZc5ebkR310b5LDdDshCicsUKh5%2BlwUgZ2ibPknFPhhYwDhVHdqmZf1H1s5t4%2Bh96LlKexHx%2F7B%2BergyYREleGZAo5fTEuJRbIKWkdCr11jnNTyfO8wySmW9usowvL7qxYx02e5oOzYWQLGFR01icUDrLIReeMxNwhZwHmhSxK9%2BjOhUdUPHgCH647DxWgjdaVyd9%2BIjv8RmZDKsV9KyLF4Gwp%2FOdiC4IkjpK3qevtRuMdhJ%2Fi0R5qbA2dt7Rs3RQbVf0cQJAXMr8SMSBaNBYsZhezRnJGpOQIX8hVYw5TdaMVI3H9rp99szcmUrKgpuYvZAD8jiOnfPAuIxfCwLGEmRxRSJkMZFXeasj5aM%2BpDT09Z46qB9Z23An7xnjoxvMj6NQ3coqevyD2XuAyCU3yQehkQojauBfyfKow3uZddmLfVmPr1I1P37aHhed5tz%2BRLTCyj9ZKQ5RVlLGEFJyOUFwgo7JWYF%2FIjV8JlJuE3VexVgWLG85%2BSaDGdbp9BB1eOvusNRs5KgLKl7GvE4AHLMo6H95%2BlnVm5297ufUn%2Bqde2ynMcA9QHvMcup%2BclWVkVCpbKZLhuLBWFOcNb5XkRUy8YsLbG%2Basz0dbhf7AXo%2BP%2BjS329DUeRL3NpXHB%2BdLZ26fFkVbQexlZi4C84%2FEbys5U%2BQL%2BZByqwBUL2yzuoPuponn3tZjf1PbMn1wvnIjcXZmjohgPE9zLCwAGIpS2iC54FvnYw37MvRyzDCIat3Fjtg7giN3Ra%2FmJ%2Bdv1yVvZKZUEh%2BfrV2CBRcpKMl713MMvpBrvz1NZ7B9PsxyuM1CmGiqXIamulWPn5z1pUZCxy3jJSs5woUhE5EgKOc1L0Rj3zof7f1ZFzFWeOu2nvToOz3q877Vz8xr%2BcPPV26kYopjz9mExDugKJ1B8gLkfoK3%2F2Wd6Pm%2BkPcH9eV8Lo9jrm7bXTpcOG052j9y49KZtNHsC5NzzMbmyOvVem%2F414478Z831nmRG89EgbpuTgjcCfbEPqyyj9B8dFB%2FIwN4RGEExEA88Y5XjUtKsUuQ9Xp7g1vHVxCJ2qpu9E0%2Bu9DHWTTVcGbpPtTQV9ZRNqIIFHwxMTqyBMbzo0xBk0wG394YF9gajjkvm34Q56CPWzuOez0cswl7%2BvDGNSlZ87AZBYmyu6ZMEM%2BfCJx9xWu29jtFZbtQ5EkR7eAPt4D1%2BoGGVvFs%2B7r9uMZc3sgI1%2F2Nw9pGr0zmqx2vEheij5G7%2BM46p5A6WHcZsJomv0ZZUq%2FrqvNzyY%2BPnYIXZy2rvkf7tCFxrKc7fwIMfqn%2Br2BG8%2BfP%2FwA9uPrkgwoAAA%3D%3D; _ga_9E363E7BES=GS1.1.1658858651.156.1.1658858694.17; _ga=GA1.1.237064537.1627842931; tmr_detect=0%7C1658858697129; buyer_from_page=main; tmr_reqNum=868");

        return result;
    }

    protected static String getBody(byte[] original) throws IOException {
        return getBody(original, "gzip");
    }

    protected static String getBody(byte[] original, String type) throws IOException {
        if ("gzip".equals(type)) {
            try(InputStream is = new GZIPInputStream(new ByteArrayInputStream(original));
                OutputStream os = new ByteArrayOutputStream()) {
                int i = -1;
                while ((i = is.read()) != -1) {
                    os.write(i);
                }

                return os.toString();
            }
        }

        if ("br".equals(type)) {
            try(InputStream is = new BrotliInputStream(new ByteArrayInputStream(original));
                OutputStream os = new ByteArrayOutputStream()) {
                int i = -1;
                while ((i = is.read()) != -1) {
                    os.write(i);
                }

                return os.toString();
            }
        }

        throw new RuntimeException("Ошибка при ");
    }

}
