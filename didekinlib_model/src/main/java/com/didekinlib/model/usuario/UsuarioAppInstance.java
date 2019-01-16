package com.didekinlib.model.usuario;

import com.didekinlib.BeanBuilder;
import com.didekinlib.crypto.EcDidekinPk;
import com.didekinlib.model.tx.TxState;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Timestamp;

import static com.didekinlib.BeanBuilder.error_message_hashcode;
import static com.didekinlib.model.usuario.UsuarioSerialNumber.USUARIO_APP_INSTANCE;

/**
 * User: pedro@didekin
 * Date: 2018-12-21
 * Time: 14:57
 */
public class UsuarioAppInstance implements TxState {

    private final long uId;
    private final TxStateId txStateId;
    private final EcDidekinPk publicKey;
    private final String gcmToken;
    private final LifeCycleEnum lifeCycle;
    private final Timestamp fechaFinKeys;

    private UsuarioAppInstance(UserAppInstBuilder builder)
    {
        uId = builder.uId;
        txStateId = builder.txStateId;
        publicKey = builder.publicKey;
        gcmToken = builder.gcmToken;
        lifeCycle = builder.lifeCycle;
        fechaFinKeys = builder.fechaFinKeys;
    }

    public long getuId()
    {
        return uId;
    }

    public EcDidekinPk getPublicKey()
    {
        return publicKey;
    }

    public String getGcmToken()
    {
        return gcmToken;
    }

    public Timestamp getFechaFinKeys()
    {
        return fechaFinKeys;
    }

    @Override
    public LifeCycleEnum getLifeCycle()
    {
        return lifeCycle;
    }

    @Override
    public TxStateId getTxStateId()
    {
        return txStateId;
    }

    // ............................ Equals and hashCode ..........................

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        UsuarioAppInstance userAppInst = (UsuarioAppInstance) obj;
        if (userAppInst.txStateId.equals(txStateId)) {
            return true;
        }
        return userAppInst.publicKey.equals(publicKey)
                && userAppInst.gcmToken.equals(gcmToken);
    }

    @Override
    public int hashCode()
    {
        if (txStateId != null) {
            return txStateId.hashCode();
        }
        if (publicKey != null && gcmToken != null) {
            return new String(publicKey.getSha256Base64EcX()).concat(gcmToken).hashCode();
        }
        throw new UnsupportedOperationException(error_message_hashcode + this.getClass().getName());
    }

    //    ========================== BUILDER ===============================

    public static class UserAppInstBuilder implements BeanBuilder<UsuarioAppInstance> {
        public long uId;
        public TxStateId txStateId;
        public EcDidekinPk publicKey;
        public String gcmToken;
        public LifeCycleEnum lifeCycle;
        public Timestamp fechaFinKeys;

        public UserAppInstBuilder()
        {
        }

        public UserAppInstBuilder uId(long uId)
        {
            this.uId = uId;
            return this;
        }

        public UserAppInstBuilder txStateId(TxStateId stateId)
        {
            txStateId = stateId;
            return this;
        }

        public UserAppInstBuilder publicKey(EcDidekinPk pk)
        {
            publicKey = pk;
            return this;
        }

        public UserAppInstBuilder gcmToken(String token)
        {
            gcmToken = token;
            return this;
        }

        public UserAppInstBuilder lifeCycle(LifeCycleEnum lifeCycleIn)
        {
            lifeCycle = lifeCycleIn;
            return this;
        }

        public UserAppInstBuilder fechaFinKeys(Timestamp fechaFin)
        {
            fechaFinKeys = fechaFin;
            return this;
        }

        @Override
        public UsuarioAppInstance build()
        {
            UsuarioAppInstance usuarioApp = new UsuarioAppInstance(this);
            if (usuarioApp.txStateId != null ||
                    (publicKey != null && gcmToken != null)) {
                return usuarioApp;
            }
            throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
        }
    }

    //    ============================= SERIALIZATION PROXY ==================================

    private static class InnerSerial implements Serializable {

        private static final long serialVersionUID = USUARIO_APP_INSTANCE.serial();

        private final long uId;
        private final TxStateId txStateId;
        private final EcDidekinPk publicKey;
        private final String gcmToken;
        private final LifeCycleEnum lifeCycle;
        private final Timestamp fechaFinKeys;

        public InnerSerial(UsuarioAppInstance userApp)
        {
            this.uId = userApp.uId;
            this.txStateId = userApp.txStateId;
            this.publicKey = userApp.publicKey;
            this.gcmToken = userApp.gcmToken;
            this.lifeCycle = userApp.lifeCycle;
            this.fechaFinKeys = userApp.fechaFinKeys;
        }

        /**
         * In the deserialization the readResolve() method of the InnerSerial object will be used.
         */
        private Object readResolve()
        {
            return new UsuarioAppInstance.UserAppInstBuilder()
                    .uId(uId)
                    .txStateId(txStateId)
                    .publicKey(publicKey)
                    .gcmToken(gcmToken)
                    .lifeCycle(lifeCycle)
                    .fechaFinKeys(fechaFinKeys)
                    .build();
        }
    }

    private Object writeReplace()
    {
        return new UsuarioAppInstance.InnerSerial(this);
    }

    private void readObject(ObjectInputStream inputStream) throws InvalidObjectException
    {
        throw new InvalidObjectException("Use innerSerial to serialize");
    }
}
