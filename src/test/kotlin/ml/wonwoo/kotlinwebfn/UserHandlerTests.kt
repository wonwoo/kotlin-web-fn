package ml.wonwoo.kotlinwebfn

import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.util.Locale
import java.util.Optional

@WebMvcTest(UserHandler::class)
@Import(RouterRouterUsersConfiguration::class)
internal class UserHandlerTests(@Autowired private val mockMvc: MockMvc) {

    @MockBean
    private lateinit var accountRepository: AccountRepository

    @SpyBean
    private lateinit var accountConverter: AccountConverter

    @Test
    fun `users test`() {

        given(accountRepository.findAll()).willReturn(listOf(
            Account(1, "wonwoo", 22),
            Account(2, "foo", 32),
            Account(3, "bar", 28)))

        mockMvc.get("/api/users") {
            accept = APPLICATION_JSON
            headers {
                contentLanguage = Locale.KOREA
            }
        }.andExpect {
            status { isOk }
            content { contentType(APPLICATION_JSON) }
            jsonPath("$[0].name") { value("wonwoo") }
            jsonPath("$[1].name") { value("foo") }
            jsonPath("$[2].name") { value("bar") }
            content { json("""[{"name":"wonwoo","age":22},{"name":"foo","age":32},{"name":"bar","age":28}]""", false) }
        }.andDo {
            print()
        }
    }


    @Test
    fun `user test`() {

        given(accountRepository.findById(1)).willReturn(
            Optional.of(Account(1, "wonwoo", 22)))

        mockMvc.get("/api/users/{id}", 1) {
            accept = APPLICATION_JSON
            headers {
                contentLanguage = Locale.KOREA
            }
        }.andExpect {
            status { isOk }
            content { contentType(APPLICATION_JSON) }
            jsonPath("$.name") { value("wonwoo") }
            content { json("""{"name":"wonwoo","age":22}""", false) }
        }.andDo {
            print()
        }
    }

    @Test
    fun `user save test`() {
        given(accountRepository.save(Account(name = "test", age = 11))).willReturn(
            Account(1, "test", 11))

        mockMvc.post("/api/users") {
            accept = APPLICATION_JSON
            contentType = APPLICATION_JSON
            content = """{"name":"test", "age":11}"""
            headers {
                contentLanguage = Locale.KOREA
            }
        }.andExpect {
            status { isOk }
            content { contentType(APPLICATION_JSON) }
            jsonPath("$.name") { value("test") }
            content { json("""{"name":"test","age":11}""", false) }
        }.andDo {
            print()
        }

    }
}