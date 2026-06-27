package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.exception.SenhaInvalidaException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FuncionarioService implements UserDetailsService{
    @Autowired
    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private PasswordEncoder encoder;

    public List<Funcionario> getFuncionarios() {
        return repository.findAll();
    }

    public Optional<Funcionario> getFuncionarioById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Funcionario salvar(Funcionario funcionario){
        validar(funcionario);
        return repository.save(funcionario);
    }

    public UserDetails autenticar(Funcionario funcionario){
        UserDetails user = loadUserByUsername(funcionario.getLogin());
        boolean senhasBatem = encoder.matches(funcionario.getSenha(), user.getPassword());

        if (senhasBatem){
            return user;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Funcionario funcionario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        //ver se é gerente ou vendedor
        String[] roles = funcionario.isAdmin()
                ? new String[]{"Gerente", "Vendedor"}
                : new String[]{"Funcionario"};

        return User
                .builder()
                .username(funcionario.getEmail())/// equivalente a getLogin
                .password(funcionario.getSenha())
                .roles(roles)
                .build();
    }

    @Transactional
    public void excluir(Funcionario funcionario) {
        Objects.requireNonNull(funcionario.getId());
        repository.delete(funcionario);
    }

    public void validar(Funcionario funcionario) {
        if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do funcionário inválido");
        }
        if (funcionario.getCpf() == null || funcionario.getCpf().isEmpty()) {
            throw new RegraNegocioException("CPF do Funcionário inválido");
        }
        if (funcionario.getEmail() == null || funcionario.getEmail().isEmpty()) {
            throw new RegraNegocioException("Email do Funcionário inválido");
        }
        if (funcionario.getSenha() == null || funcionario.getSenha().isEmpty()) {
            throw new RegraNegocioException("Senha do Funcionário inválida");
        }
        if (funcionario.getEndereco() == null || funcionario.getEndereco().isEmpty()) {
            throw new RegraNegocioException("Endereço do Funcionário inválido");
        }
        if (funcionario.getNumTelefone() == null || funcionario.getNumTelefone().isEmpty()) {
            throw new RegraNegocioException("Número de telefone do Funcionário inválido");
        }
    }
}
