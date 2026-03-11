# Desafio: Componentes e Injeção de Dependência

## 📌 Descrição

Este projeto é um **desafio prático** para aprender e aplicar os conceitos fundamentais de **Injeção de Dependência (DI)** e **Componentes Spring** no contexto de uma aplicação real.

Faz parte do curso **Java Spring Professional** do Prof. Nélio Alves como um exercício prático para fixar conceitos.

## 🎯 Objetivos de Aprendizado

- Entender o conceito de Injeção de Dependência
- Usar anotações do Spring (@Component, @Service, @Repository, @Autowired)
- Configurar beans manualmente com @Bean e @Configuration
- Entender o ciclo de vida dos componentes Spring
- Aplicar padrões de design (Singleton, Factory)
- Resolver problemas de circularidade de dependências
- Escrever código desacoplado e testável

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot**
- **Maven**

## 📋 Requisitos

- Java 21 instalado
- Maven 3.8+ instalado

## 🚀 Como Executar

### 1. Navegar para o projeto
```bash
cd java-spring-professional/desafio-componentes-injecao-dependencia
```

### 2. Compilar o projeto
```bash
mvn clean install
```

### 3. Executar a aplicação
```bash
mvn spring-boot:run
```

Ou execute a classe principal diretamente em sua IDE.

## 📚 Conceitos Abordados

### 1. **Componentes Spring (@Component)**
```java
@Component
public class MinhaClasse {
    // Classe será gerenciada pelo Spring
}
```

### 2. **Injeção de Dependência**
```java
@Component
public class Servico {
    @Autowired
    private Repositorio repositorio;
}
```

### 3. **Estereótipos (Stereotypes)**
- `@Component` - Componente genérico
- `@Service` - Serviço de negócio
- `@Repository` - Acesso a dados
- `@Controller` - Controlador MVC
- `@RestController` - Controlador REST

### 4. **Configuração Manual de Beans**
```java
@Configuration
public class MinhaConfiguracao {
    @Bean
    public Objeto meuBean() {
        return new Objeto();
    }
}
```

### 5. **Ciclo de Vida dos Beans**
- `@PostConstruct` - Executado após inicialização
- `@PreDestroy` - Executado antes de destruição
- `InitializingBean` interface
- `DisposableBean` interface

### 6. **Qualificadores**
```java
@Autowired
@Qualifier("nomeDoBean")
private Dependencia dependencia;
```

### 7. **Value Injection**
```java
@Value("${propriedade.chave}")
private String valor;
```

## 🎯 Estrutura Esperada do Desafio

O projeto típico tem a seguinte estrutura:

```
desafio-componentes-injecao-dependencia/
├── src/main/java/
│   └── com/devsuperior/
│       ├── config/
│       │   └── Configuração de beans
│       ├── service/
│       │   └── Serviços de negócio
│       ├── repository/
│       │   └── Acesso a dados
│       └── Application.java
└── src/main/resources/
    └── application.properties
```

## 💡 Exemplo de Implementação

### Componente de Serviço
```java
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repositorio;
    
    public void salvarCliente(Cliente cliente) {
        repositorio.save(cliente);
    }
}
```

### Componente de Repositório
```java
@Repository
public class ClienteRepository {
    // Implementação do repositório
}
```

### Configuração de Beans
```java
@Configuration
public class MeuConfig {
    
    @Bean
    public Servico meuServico() {
        return new Servico();
    }
}
```

## 🧪 Testando a Aplicação

### Verificar injeção de dependência:
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        // Obter bean do contexto
        MinhaClasse objeto = ctx.getBean(MinhaClasse.class);
        objeto.executar();
    }
}
```

## ⚠️ Problemas Comuns e Soluções

### 1. **NoSuchBeanDefinitionException**
- **Causa**: Bean não foi registrado no contexto Spring
- **Solução**: Adicionar @Component, @Service, @Repository ou declarar @Bean

### 2. **UnsatisfiedDependencyException**
- **Causa**: Dependência não pode ser injetada
- **Solução**: Garantir que a dependência está disponível como bean

### 3. **Circular Dependency**
- **Causa**: Dois beans dependem um do outro
- **Solução**: Refatorar código ou usar @Lazy para inicialização tardia

### 4. **Multiple Beans of Same Type**
- **Causa**: Múltiplos beans do mesmo tipo
- **Solução**: Usar @Qualifier ou @Primary

## 📋 Checklist do Desafio

- [ ] Criar componentes com @Component/@Service/@Repository
- [ ] Injetar dependências com @Autowired
- [ ] Configurar beans manualmente com @Bean
- [ ] Usar @Qualifier para resolver múltiplos beans
- [ ] Implementar @PostConstruct e @PreDestroy
- [ ] Usar @Value para injetar propriedades
- [ ] Resolver problemas de dependência circular
- [ ] Escrever testes para os componentes

## 🔧 Próximas Melhorias

- [ ] Testes unitários com JUnit e Mockito
- [ ] Uso de profiles (dev, test, prod)
- [ ] Injeção de lista de beans
- [ ] Factory pattern com Spring
- [ ] Aspect-Oriented Programming (AOP)

## 📚 Recursos Úteis

- [Spring Framework Documentation](https://docs.spring.io/spring-framework/reference/)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/reference/)
- [Baeldung - Spring Dependency Injection](https://www.baeldung.com/spring-dependency-injection)

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓

